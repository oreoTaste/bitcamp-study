package com.eomcs.lms;

// v32_3 -> v32_4로 연습중

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

  // listner(Observer) 관리하는 list
  Set<ApplicationContextListener> listeners = new HashSet<>();

  // Object에 배열을 담아서 보내줌.
  Map<String, Object> context = new HashMap<>();

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

    try (// 서버쪽 연결준비
        ServerSocket serverSocket = new ServerSocket(9999)
        ) {
      while(true) {
        System.out.println("...클라이언트 연결 대기중");

        // 서버에 대기중인 클라이언트와 연결
        // => 대기하고 있는 클라이언트와 연결될때까지 리턴하지 않는다. (blocking 방식)
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결됨");
        if(processRequest(socket) == 9) {
          break;
        }

        System.out.println("=========================");
      }

    } catch (Exception e) {
      System.out.println("서버 실행중 오류발생" + e.getMessage());
    }

    notifyApplicationDestroyed();
  }

  public static void main(String[] args) {
    System.out.println("서버 애플리케이션 실행");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();


  }

  @SuppressWarnings("unchecked")
  int processRequest(Socket clientSocket) {
    try(
        // 요청처리가 끝난후 클라이언트와 연결된 소켓을 자동으로 닫으려면
        // 이 괄호 안에 별도의 로컬 변수에 담는다.
        Socket socket = clientSocket;

        // 클라이언트의 메시지를 수신하고 전송할 입출력 도구를 준비
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())
        ) {
      while(true) {
        System.out.println("통신을 위한 입출력 스트림을 준비함");

        // 클라이언트가 보낸 메시지를 수신한다.
        // => 한줄의 메시지를 읽을때까지 리턴하지 않는다. (blocking 방식)
        String request = in.readUTF();

        if (request.length() == 0) {
          break;
        }

        if (request.equalsIgnoreCase("quit")) {
          out.writeUTF("OK");
          out.flush();
          break;
        }

        if(request.equalsIgnoreCase("/server/stop")) {
          out.writeUTF("OK");
          out.flush();
          return 9;
        }

        List<Board> boards = (List<Board>)context.get("boardList");
        //List<Lesson> lessons = (List<Lesson>)context.get("lessonList");
        //List<Member> members = (List<Member>)context.get("memberList");

        if(request.equals("/board/add")) {
          try {
            Board board = (Board) in.readObject();
            for(Board b : boards) {
              if(b.getNo() == board.getNo()) {
                out.writeUTF("OK");
                break;
              }
              out.writeUTF("FAIL");
              out.writeUTF("해당 번호의 게시물이 없습니다.");
            }
          } catch(Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if(request.equals("/board/list")) {

          try {
            out.writeUTF("OK");
            out.reset();
            out.writeObject(boards);
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF("게시물 목록을 전송하는중 오류가 발생하였습니다.");
          }

        } else if(request.equals("/board/detail")) {

          try {
            int no = in.readInt();
            for(Board b : boards) {
              if(b.getNo() == no) {
                out.writeUTF("OK");
                out.writeObject(b);
                break;
              }
            } out.writeUTF("FAIL");
            out.writeUTF("해당 번호의 게시물이 없습니다.");
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        } else if(request.equals("/board/update")) {

          try {
            Board board = (Board) in.readObject();
            for(Board b : boards) {
              if(b.getNo() == board.getNo()) {
                out.writeUTF("OK");
                break;
              }
            } out.writeUTF("FAIL");
            out.writeUTF("해당 번호의 게시물이 없습니다.");
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }

        } else if(request.equals("/board/delete")) {
          
          try {
          int no = in.readInt();
          
          int index = -1;
          for(int i = 0 ; i < boards.size(); i++) {
            if(boards.get(i).getNo() == no) {
              index = i;
              break;
            }
          }
          
          if(index == -1) {
            out.writeUTF("FAIL");
            out.writeUTF("게시물 없음");
            break;
          } boards.remove(index);
          out.writeUTF("OK");
          } catch (Exception e) {
            out.writeUTF("FAIL");
            out.writeUTF(e.getMessage());
          }
        }

      }

      return 0;
    } catch (Exception e) {
      System.out.println("예외발생 : " + e.getMessage());
      e.printStackTrace();
      return -1;
    }
  }



}
