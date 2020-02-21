package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.json.BoardJsonFileDao;
import com.eomcs.lms.dao.json.LessonJsonFileDao;
import com.eomcs.lms.dao.json.MemberJsonFileDao;
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
import com.eomcs.lms.servlet.MemberUpdateServlet;
import com.eomcs.lms.servlet.Servlet;

public class ServerApp {

  // 커맨드 디자인 패턴과 관련된 객체 준비
  Map<String, Servlet> servletMap = new HashMap<>();

  Set<ApplicationContextListener> listeners = new HashSet<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);

  }
  Map<String, Object> context = new HashMap<>();

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

    // DataLoaderListener가 준비한 Dao 객체를 꺼내 변수에 저장한다.
    //BoardObjectFileDao boardDao = (BoardObjectFileDao) context.get("boardDao");
    BoardDao boardDao = (BoardJsonFileDao) context.get("boardDao");
    //LessonObjectFileDao lessonDao = (LessonObjectFileDao) context.get("lessonDao");
    LessonDao lessonDao = (LessonJsonFileDao) context.get("lessonDao");
    //MemberObjectFileDao memberDao = (MemberObjectFileDao) context.get("memberDao");
    MemberDao memberDao = (MemberJsonFileDao) context.get("memberDao");

    // 커맨드 객체 역할을 수행하는 서블릿 객체를 맵에 저장한다.
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
    
    try(ServerSocket serverSocket = new ServerSocket(9999)) {

      while(true) {
        System.out.println("...클라이언트 연결 대기중");

        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결됨");
        processRequest(socket);

        System.out.println("=========================");
      }

    } catch (IOException e) {
      System.out.println("서버준비중 오류발생");
    }

    notifyApplicationDestroyed();
  }

  void processRequest(Socket clientSocket) {
    try(Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

        String request = in.nextLine();
        System.out.printf("=>%s\n",request);

        out.println("[영국] 안녕하세요!");
        out.println("[영국] 반가워요!");
        out.println("!end!");
        
        /*
        if(request.equalsIgnoreCase("/server/stop") {
          quit(out);
          return 9; // 서버를 종료한다.
        }
        */
        /*
        // 클라이언트의 요청을 처리할 객체를 찾는다.
        Servlet servlet = servletMap.get(request);

        if (servlet != null) {
          // 클라이언트 요청을 처리할 객체를 찾았으면 작업을 실행시킨다.
          try {
            servlet.service(in, out);

          } catch (Exception e) {
            // 요청한 작업을 수행하다가 오류 발생할 경우 그 이유를 간단히 응답한다.
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());

            // 서버쪽 화면에는 더 자세하게 오류 내용을 출력한다.
            System.out.println("클라이언트 요청 처리 중 오류 발생:");
            e.printStackTrace();
          }
        } else { // 없다면? 간단한 아내 메시지를 응답한다.
          notFound(out);
        }
        */
        
        out.flush();
        System.out.println("클라이언트에게 응답하였음!");
        System.out.println("------------------------------------");
        
    } catch (Exception e) {
      System.out.println("예외발생 : " + e.getMessage());
      e.printStackTrace();
      //return -1;
    }
  }

  private void notFound(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  public static void main(String[] args) {
    System.out.println("서버 애플리케이션 실행");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();

  }

}
