package com.eomcs.lms;

import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

public class ServerApp {
  
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
  
  @SuppressWarnings("unchecked")
  public void service() {
    
    notifyApplicationInitialized();
    
    List<Board> boardList = (List<Board>)context.get("boardList");
    List<Lesson> lessonList = (List<Lesson>)context.get("lessonList");
    List<Member> memberList = (List<Member>)context.get("memberList");
    
    notifyApplicationDestroyed();
  }
  
  public static void main(String[] args) {
    System.out.println("서버 애플리케이션 실행");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
    
    /*
    try (// 서버쪽 연결준비
        ServerSocket serverSocket = new ServerSocket(9999)
        ) {
      while(true) {
        System.out.println("...클라이언트 연결 대기중");
        
        // 서버에 대기중인 클라이언트와 연결
        // => 대기하고 있는 클라이언트와 연결될때까지 리턴하지 않는다. (blocking 방식)
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결됨");
        processRequest(socket);
        
        System.out.println("=========================");
      }

    } catch (Exception e) {
      System.out.println("서버 실행중 오류발생" + e.getMessage());
    }
    */
  }

  static void processRequest(Socket clientSocket) {
    try(
        // 요청처리가 끝난후 클라이언트와 연결된 소켓을 자동으로 닫으려면
        // 이 괄호 안에 별도의 로컬 변수에 담는다.
        Socket socket = clientSocket;

        // 클라이언트의 메시지를 수신하고 전송할 입출력 도구를 준비
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())
        ) {
      System.out.println("통신을 위한 입출력 스트림을 준비함");

      // 클라이언트가 보낸 메시지를 수신한다.
      // => 한줄의 메시지를 읽을때까지 리턴하지 않는다. (blocking 방식)
      String message = in.nextLine();
      System.out.println("클라이언트가 보낸 메시지를 수신함 : " + message);

      // 클라이언트에게 메시지를 전송한다.
      // => 클라이언트에서 읽을때까지 리턴하지 않는다. (blocking 방식)
      out.println("Hi(손영국)");
      System.out.println("클라이언트로 메시지를 전송함.");

    } catch (Exception e) {
      System.out.println("예외발생 : " + e.getMessage());
      e.printStackTrace();
    }
  }


}
