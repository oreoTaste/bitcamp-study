package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ConnectionClosedException;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ExceptionListener;
import org.apache.hc.core5.http.HttpConnection;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.impl.bootstrap.HttpServer;
import org.apache.hc.core5.http.impl.bootstrap.ServerBootstrap;
import org.apache.hc.core5.http.io.HttpRequestHandler;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.util.TimeValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.util.RequestHandler;
import com.eomcs.util.RequestMappingHandlerMapping;

public class ServerApp implements ExceptionListener, HttpRequestHandler{
  // log4j의 logger 준비
  static Logger logger = LogManager.getLogger(ServerApp.class);

  // 커맨드 디자인 패턴과 관련된 객체 준비
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  boolean serverStop = false;

  RequestMappingHandlerMapping handlerMapper;
  // IoC 컨테이너 준비
  ApplicationContext iocContainer;

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }


  private void notifyApplicationInitialized() {
    for(ApplicationContextListener listener : listeners) {
      listener.contextInitialized(context);
    }
  }

  private void notifyApplicationDestroyed() {
    for(ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(context);
    }
  }

  public void service() throws Exception {
    notifyApplicationInitialized();

    handlerMapper =
        (RequestMappingHandlerMapping) context.get("handlerMapper");

    // ApplicationContext(IoC 컨테이너)를 꺼낸다.
    iocContainer = (ApplicationContext) context.get("iocContainer");

    // 소켓 동작 설정
    final SocketConfig socketConfig = SocketConfig.custom()
        .setSoTimeout(3, TimeUnit.SECONDS)
        .setTcpNoDelay(true)
        .build();
    
    // http 서버 준비
    final HttpServer server = ServerBootstrap.bootstrap()
        .setListenerPort(9999) // 웹서버 포트 번호 설정
        .setSocketConfig(socketConfig) // 기본소켓 동작 설정
        .setSslContext(null) // SSL 설정
        .setExceptionListener(this)
        .register("*", this) // 요청처리자 설정
        .create();
    
    server.start();
    Runtime.getRuntime().addShutdownHook(new Thread() { //웹서버를 종료시키는 스레드를 등록
      @Override
      public void run() {
        notifyApplicationDestroyed();
        logger.info("서버종료");
        server.close(CloseMode.GRACEFUL);
      }
    });
    System.out.println("서버시작!");
    server.awaitTermination(TimeValue.MAX_VALUE);
  }

  private String getServletPath(String requestUri) {
    return requestUri.split("\\?")[0];              // board/add
  }
  
  private Map<String, String> getParameters(String requestUri) throws Exception {
    String[] items = requestUri.split("\\?");
    Map<String, String> params = new HashMap<>();
    if(items.length > 1) {
      String[] entries = items[1].split("&");
      logger.debug(String.format("query string :%s", items[1]));
      for(String entry : entries) {
        logger.debug(String.format("parameter :%s", entry));
        String[] kv = entry.split("=");
        logger.debug(String.format("key value :%s, %s", kv[0], kv[1]));
        String value = URLDecoder.decode(kv[1], "UTF-8");
        params.put(kv[0], value);
      }
    }
    return params;
  }

  private void notFound(PrintWriter out) throws IOException {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글 입력</title>");
    out.println("</head>");
      
    out.println("<body style=background:silver>");

    out.println("<h1>실행오류</h1>");
    out.println("<p>요청한 명령을 처리할 수 없습니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }
  
  private void error(PrintWriter out, Exception e) throws IOException {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글 입력</title>");
    out.println("</head>");
      
    out.println("<body style=background:silver>");

    out.println("<h1>실행오류</h1>");
    out.printf("<p>%s</p>", e.getMessage());
    out.println("</body>");
    out.println("</html>");
  }

  public static void main(String[] args) throws Exception {
    logger.info("서버 애플리케이션 실행");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();

  }

  // Exception Listener 구현
  @Override
  public void onError(final Exception ex) {
    ex.printStackTrace();
  }

  // Exception Listener 구현
  @Override
  public void onError(final HttpConnection conn, final Exception ex) {
    if (ex instanceof SocketTimeoutException) {
      System.err.println("Connection timed out");
    } else if (ex instanceof ConnectionClosedException) {
      System.err.println(ex.getMessage());
    } else {
      ex.printStackTrace();
    }
  }

  // Request Handler 구현
  @Override
  public void handle(
      final ClassicHttpRequest request,
      final ClassicHttpResponse response,
      final HttpContext context)
      throws HttpException, IOException {
    
    logger.info("=========================");
    logger.info("클라이언트와 연결됨");
    
    // 클라이언트로 출력할 때, 사용할 도구준비
    StringWriter outBuffer = new StringWriter();
    PrintWriter out = new PrintWriter(outBuffer);
    // 이 출력 스트림을 사용하여 출력하는 모든 데이터는, 메모리에 임시보관된다
    
//////////////////////////////////////////////////////////////////////////////////////////////////

    String method = request.getMethod(); // GET || POST || PUT || DELETE || PATCH
    String requestUri = request.getPath(); // /board/list?value1=100&value2=200;
    logger.info("method : {}, path : {}", method, requestUri);
    // 확인결과 : path가 request line입니다.
    
    try {
      String servletPath = getServletPath(requestUri);
      logger.debug(String.format("servlet path :%s", servletPath));
      
      Map<String, String> params = getParameters(requestUri);
      RequestHandler requestHandler = handlerMapper.getHandler(servletPath);
      
      if(requestHandler != null) {
          requestHandler.getMethod().invoke(requestHandler.getBean(), params, out);

      } else {
        notFound(out);
        logger.info("해당 명령을 지원하지 않습니다.");
      }
      
    } catch (Exception e) {
      error(out, e);

      out.println("요청처리중 오류 발생!");
      out.println(e.getMessage());
      
      logger.info("클라이언트 요청 처리중 오류발생");
      logger.info(e.getMessage());
      StringWriter stringWriter = new StringWriter();
      e.printStackTrace(new PrintWriter(stringWriter));
      logger.debug(stringWriter);
    }

    out.flush();
    
//////////////////////////////////////////////////////////////////////////////////////////////////
    
    response.setCode(HttpStatus.SC_OK); // HTTP/1.1 200 OK
    response.setEntity(new StringEntity(
        outBuffer.toString(),
        ContentType.create("text/html", Charset.forName("UTF-8"))));
    logger.info("클라이언트에게 응답하였음!");
  }

}
