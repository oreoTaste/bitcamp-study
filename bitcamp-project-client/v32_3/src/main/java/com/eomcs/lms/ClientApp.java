// LMS 클라이언트
package com.eomcs.lms;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.prompt.Prompt;

public class ClientApp {
  java.io.InputStream inputStream = System.in;
  java.util.Scanner keyboard = new java.util.Scanner(inputStream);
  Prompt prompt = new Prompt(keyboard);
  
  public void service() {
    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();
    String command;
    HashMap<String, Command> hashmap = new HashMap<>();

    while (true) {
      command = prompt.inputString("\n명령> ");
      commandStack.push(command);
      commandQueue.offer(command);

      if (command.length() == 0) {
        continue;
      }
      if (command.equalsIgnoreCase("quit")) {
        System.out.println("...안녕!");
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandStack.iterator());
        continue;
      }

      Command commandHandler = hashmap.get(command);
      if (commandHandler != null) {
        try {
          commandHandler.execute();
        } catch(Exception e) {
          System.out.printf("명령어 실행 중 오류발생 : %s\n", e.getMessage());
        }
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
    keyboard.close();
  }
  
  
  public static void main(String[] args) throws Exception {
    System.out.println("클라이언트 수업관리 시스템입니다");
    
    ClientApp app = new ClientApp();
    app.service();
    
    /*
    String serverAddr = null;
    int port = 0;
    
 // 키보드 스캐너 준비
    Scanner keyScan = new Scanner(System.in);
    
    try{
      //사용자로부터 접속할 서버의 주소와 포트번호를 입력받는다.
      System.out.print("서버? ");
      serverAddr = keyScan.nextLine();
      
      System.out.print("포트? ");
      port = Integer.parseInt(keyScan.nextLine());
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage());
      keyScan.close();
      return;
    }

    try(
        // 서버와 연결하기
        // 127.0.0.1 자기자신
        // new Socket("주소", 포트번호);
        Socket socket = new Socket(serverAddr, port);

        // 소켓을 통해 데이터를 읽고 쓰는 도구를 준비한다.
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner (socket.getInputStream())
        ) {

      // 서버에 연결
      System.out.print("서버에 보낼 메시지 입력:");
      String sendMsg = keyScan.nextLine();
      
      // 서버에 메시지를 전송한다.
      // => 서버가 메시지를 받을 때까지 리턴하지 않는다. (blocking 방식)
      out.println(sendMsg);
      System.out.println("서버에 메시지 전송함 : hello");

      // 서버가 응답한 메시지를 수신한다.
      // => 서버로부터 한줄의 메시지를 받을때까지 리턴하지 않는다. (blocking 방식)
      String message = in.nextLine();
      System.out.println("서버에 메시지 수신함");

      // 서버가 받은 메시지를 출력한다.
      System.out.println("서버 : " + message);

      System.out.println("서버와 연결종료");
    } catch (Exception e) {
      System.out.println("예외발생 : " + e.getMessage());
    }
    
    
    keyScan.close();
    */
  }
  
  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;

    while (iterator.hasNext()) {
      System.out.println(iterator.next());

      if (++count % 5 == 0) {
        System.out.print(": (중지하고 싶으면 q)");
        String str = prompt.inputString("");
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
  
  
}
