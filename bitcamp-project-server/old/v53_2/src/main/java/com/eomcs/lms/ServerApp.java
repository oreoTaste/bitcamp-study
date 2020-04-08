package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.util.RequestHandler;
import com.eomcs.util.RequestMappingHandlerMapping;

public class ServerApp {
  // log4j의 logger 준비
  static Logger logger = LogManager.getLogger(ServerApp.class);

  // 커맨드 디자인 패턴과 관련된 객체 준비
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  boolean serverStop = false;
  ExecutorService executorService = Executors.newCachedThreadPool();

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

  public void service() {
    notifyApplicationInitialized();

    handlerMapper =
        (RequestMappingHandlerMapping) context.get("handlerMapper");

    // ApplicationContext(IoC 컨테이너)를 꺼낸다.
    iocContainer = (ApplicationContext) context.get("iocContainer");

    try(ServerSocket serverSocket = new ServerSocket(9999)) {

      logger.info("...클라이언트 연결 대기중");

      while(true) {
        Socket socket = serverSocket.accept();
        logger.info("클라이언트와 연결됨");

        executorService.submit(() -> {
          processRequest(socket);
          logger.info("=========================");
        });

        // 이전 클라이언트에서 서버멈춤요청이 있다면,
        // 즉시 반복문을 멈추고 서버를 종료한다.
        if(serverStop) {
          break;
        }
      }

    } catch (Exception e) {
      logger.error(String.format("서버준비중 오류발생 : %s", e.getMessage()));
    }

    // 모든 스레드가 끝났는지 검사하면서 기다리기.
    while(true) {
      if(executorService.isTerminated()) {
        break;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    executorService.shutdown();
    // 클라이언트 요청을 처리하는 스레드가 모두 종료된 후에 DB 커넥션을 닫도록 한다.
    notifyApplicationDestroyed();
    logger.info("서버종료");
  }

  void processRequest(Socket clientSocket) {
    try(Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String[] requestLine = in.nextLine().split(" ");
      while(true) {
        String line = in.nextLine();
        if(line.length() == 0) {
          break;
        }
      }
      String method = requestLine[0];
      logger.info(String.format("method => %s", method));
      String requestUri = requestLine[1];
      logger.info(String.format("requestUri => %s", requestUri));
      
      String servletPath = getServletPath(requestUri);
      logger.debug(String.format("servlet path :%s", servletPath));
      printResponseHeader(out);
      
      Map<String, String> params = getParameters(requestUri);
      

      if(servletPath.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }

      RequestHandler requestHandler = handlerMapper.getHandler(servletPath);
      if(requestHandler != null) {
        try {
          //          servlet.service(in, out);
          requestHandler.getMethod().invoke(requestHandler.getBean(), params, out);
        } catch (Exception e) {
          out.println("요청처리중 오류 발생!");
          out.println(e.getMessage());

          logger.info("클라이언트 요청 처리중 오류발생");
          logger.info(e.getMessage());
          StringWriter stringWriter = new StringWriter();
          e.printStackTrace(new PrintWriter(stringWriter));
          logger.debug(stringWriter);
        }
      } else {
        notFound(out);
        logger.info("해당 명령을 지원하지 않습니다.");
      }

      out.flush();
      logger.info("클라이언트에게 응답하였음!");

    } catch (Exception e) {
      logger.error(String.format("예외발생 : %s", e.getMessage()));
      StringWriter stringWriter = new StringWriter();
      e.printStackTrace(new PrintWriter(stringWriter));
      logger.debug(stringWriter);
    }

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

  private void quit(PrintStream out) throws IOException {
    serverStop = true;
    out.println("OK");
    out.flush();
  }
  
  private void printResponseHeader(PrintStream out) {
    out.println("HTTP/1.1 200 OK");
    out.println("Server : bitcamp Server");
    out.println();
  }

  private void notFound(PrintStream out) throws IOException {
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

  public static void main(String[] args) {
    logger.info("서버 애플리케이션 실행");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();

  }

}
