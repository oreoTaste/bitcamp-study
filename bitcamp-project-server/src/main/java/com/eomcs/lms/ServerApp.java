package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.domain.Board;

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

  public void service() {

    notifyApplicationInitialized();

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

  @SuppressWarnings("unchecked")
  void processRequest(Socket clientSocket) {
    try(
        Socket socket = clientSocket;

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream())
        ) {
      System.out.println("통신을 위한 입출력 스트림을 준비함");
      List<Board> boardList = (List<Board>) context.get("boardList");
      
      String request = in.readUTF();
      System.out.println("클라이언트가 보낸 메시지를 수신함 : " + request);

      if(request.equals("/board/list")) {
        out.writeUTF("OK");
        out.writeObject(boardList);
      } else if(request.equals("/board/add")) {
        Board board = (Board) in.readObject();
        boardList.add(board);
      } else {
        out.writeUTF("FAIL");
        out.writeUTF("요청한 명령을 처리할 수 없습니다.");
      }
      out.flush(); // 서버에 데이터를 즉시 전송하도록 임시 버퍼의 내용을 방출한다.
      System.out.println("클라이언트로 메시지를 전송함.");

    } catch (Exception e) {
      System.out.println("예외발생 : " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    System.out.println("서버 애플리케이션 실행");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();

  }

}
