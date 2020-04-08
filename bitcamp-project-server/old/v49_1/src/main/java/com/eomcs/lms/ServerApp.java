package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.util.RequestHandler;
import com.eomcs.util.RequestMappingHandlerMapping;

public class ServerApp {

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
      System.out.println("...클라이언트 연결 대기중");

      while(true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결됨");

        executorService.submit(() -> {
          processRequest(socket);
          System.out.println("=========================");
        });

        // 이전 클라이언트에서 서버멈춤요청이 있다면,
        // 즉시 반복문을 멈추고 서버를 종료한다.
        if(serverStop) {
          break;
        }
      }

    } catch (Exception e) {
      System.out.println("서버준비중 오류발생");
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
    System.out.println("서버종료");
  }

  void processRequest(Socket clientSocket) {
    try(Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      System.out.printf("=> %s\n",request);

      if(request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return;
      }

      RequestHandler requestHandler = handlerMapper.getHandler(request);
      if(requestHandler != null) {
        try {
//          servlet.service(in, out);
          requestHandler.getMethod().invoke(requestHandler.getBean(), in, out);
        } catch (Exception e) {
          out.println("요청처리중 오류 발생!");
          out.println(e.getMessage());

          System.out.println("클라이언트 요청 처리중 오류발생");
          e.printStackTrace();

        }
      } else {
        notFound(out);
      }


      out.println("!end!");

      out.flush();
      System.out.println("클라이언트에게 응답하였음!");

    } catch (Exception e) {
      System.out.println("예외발생 : " + e.getMessage());
      e.printStackTrace();
    }

  }

  private void quit(PrintStream out) throws IOException {
    serverStop = true;
    out.println("OK");
    out.println("!end!");
    out.flush();
  }

  private void notFound(PrintStream out) throws IOException {
    out.println("요청한 명령을 처리할 수 없습니다.");
  }

  public static void main(String[] args) {
    System.out.println("서버 애플리케이션 실행");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new ContextLoaderListener());
    app.service();

  }

}
