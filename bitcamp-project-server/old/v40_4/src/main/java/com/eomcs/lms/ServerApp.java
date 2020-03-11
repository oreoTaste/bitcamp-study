package com.eomcs.lms;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.servlet.BoardAddServlet;
import com.eomcs.lms.servlet.BoardDeleteServlet;
import com.eomcs.lms.servlet.BoardDetailServlet;
import com.eomcs.lms.servlet.BoardListServlet;
import com.eomcs.lms.servlet.BoardUpdateServlet;
import com.eomcs.lms.servlet.LessonAddServlet;
import com.eomcs.lms.servlet.LessonDeleteServlet;
import com.eomcs.lms.servlet.LessonDetailServlet;
import com.eomcs.lms.servlet.LessonListServlet;
import com.eomcs.lms.servlet.LessonUpdateServlet;
import com.eomcs.lms.servlet.MemberAddServlet;
import com.eomcs.lms.servlet.MemberDeleteServlet;
import com.eomcs.lms.servlet.MemberDetailServlet;
import com.eomcs.lms.servlet.MemberListServlet;
import com.eomcs.lms.servlet.MemberSearchServlet;
import com.eomcs.lms.servlet.MemberUpdateServlet;
import com.eomcs.lms.servlet.PhotoBoardAddServlet;
import com.eomcs.lms.servlet.PhotoBoardDeleteServlet;
import com.eomcs.lms.servlet.PhotoBoardDetailServlet;
import com.eomcs.lms.servlet.PhotoBoardListServlet;
import com.eomcs.lms.servlet.PhotoBoardUpdateServlet;
import com.eomcs.lms.servlet.Servlet;
import com.eomcs.sql.ConnectionProxy;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.util.ConnectionFactory;

public class ServerApp {

  // 커맨드 디자인 패턴과 관련된 객체 준비
  Map<String, Servlet> servletMap = new HashMap<>();
  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();
  boolean serverStop = false;
  ExecutorService executorService = Executors.newCachedThreadPool();

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

    ConnectionFactory conFactory = (ConnectionFactory) context.get("connectionFactory");
    PlatformTransactionManager txManager =
        (PlatformTransactionManager) context.get("transactionManager");

    BoardDao boardDao = (BoardDao) context.get("boardDao");
    LessonDao lessonDao = (LessonDao) context.get("lessonDao");
    MemberDao memberDao = (MemberDao) context.get("memberDao");
    PhotoBoardDao photoBoardDao = (PhotoBoardDao) context.get("photoBoardDao");
    PhotoFileDao photoFileDao = (PhotoFileDao) context.get("photoFileDao");

    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/update", new BoardUpdateServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));

    servletMap.put("/lesson/list", new LessonListServlet(lessonDao));
    servletMap.put("/lesson/add", new LessonAddServlet(lessonDao));
    servletMap.put("/lesson/detail", new LessonDetailServlet(lessonDao));
    servletMap.put("/lesson/update", new LessonUpdateServlet(lessonDao));
    servletMap.put("/lesson/delete", new LessonDeleteServlet(lessonDao));

    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/update", new MemberUpdateServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));
    servletMap.put("/member/search", new MemberSearchServlet(memberDao));

    servletMap.put("/photoboard/list",
        new PhotoBoardListServlet(photoBoardDao, lessonDao));
    servletMap.put("/photoboard/detail",
        new PhotoBoardDetailServlet(photoBoardDao, photoFileDao));
    servletMap.put("/photoboard/add",
        new PhotoBoardAddServlet(txManager, photoBoardDao, lessonDao, photoFileDao));
    servletMap.put("/photoboard/update",
        new PhotoBoardUpdateServlet(txManager, photoBoardDao, photoFileDao));
    servletMap.put("/photoboard/delete",
        new PhotoBoardDeleteServlet(txManager, photoBoardDao, photoFileDao));

    try(ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("...클라이언트 연결 대기중");

      while(true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결됨");


        executorService.submit(() -> {

          processRequest(socket);

          try(Connection con = conFactory.removeConnection()) {
            if(con != null) {
              ((ConnectionProxy)con).realClose();
            }
          } catch (Exception e) {
            
          }


        });

        System.out.println("=========================");
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

      Servlet servlet = servletMap.get(request);

      if(servlet != null) {
        try {
          servlet.service(in, out);
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
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();

  }

}
