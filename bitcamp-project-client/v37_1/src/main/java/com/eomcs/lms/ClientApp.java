package com.eomcs.lms;

import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.prompt.Prompt;

public class ClientApp {

  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  public ClientApp() throws ClassNotFoundException, SQLException {
    // 사용자가 입력한 명령어를 보관할 객체 준비
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();
  }

  public void service() {

    while (true) {
      String command;
      command = prompt.inputString("\n명령> ");

      if (command.length() == 0) {
        continue;
      }

      if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      } else if (command.equals("quit")) {
        break;
      }

      commandStack.push(command);
      commandQueue.offer(command);
      processCommand(command);
    }

    keyboard.close();
  }

  private void processCommand(String command) {
    // 명령어 형식을 변경!
    // [기존방식] : /board/list
    // [새 방식] : bitcamp://서버주소:포트번호/board/list

    String host = null;
    int port = 9999;
    String servletPath = null;

    try {
      if(!command.startsWith("bitcamp://"))
        throw new Exception("명령어 형식이 옳지 않습니다");

      // System.out.println(command);
      String url = command.substring(10);
      // System.out.println(url);

      int index = url.indexOf("/");

      String[] str = url.substring(0,index).split(":");
      host = str[0];

      if(str.length == 2) {
        port = Integer.parseInt(str[1]);
      }
      // System.out.printf("%s:%d\n", host, port);

      // 명령어부분
      servletPath = url.substring(index);
      System.out.println(servletPath);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    try(Socket socket = new Socket(host, port);
        PrintStream out = new PrintStream(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream())) {

      out.println(servletPath);
      out.flush();

      while(true) {
        String response = in.nextLine();
        if(response.equalsIgnoreCase("!end!")) {
          break;
        }
        System.out.println(response);
      }


    } catch(Exception e) {
      System.out.println(e.getMessage());
    }


  }

  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      count++;

      if ((count % 5) == 0) {
        String str = prompt.inputString(":");
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("클라이언트 수업 관리 시스템입니다.");

    ClientApp app = new ClientApp();
    app.service();
  }
}
