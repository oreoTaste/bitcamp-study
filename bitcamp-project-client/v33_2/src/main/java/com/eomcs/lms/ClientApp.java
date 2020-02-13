// LMS 클라이언트
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.dao.proxy.BoardDaoProxy;
import com.eomcs.lms.dao.proxy.LessonDaoProxy;
import com.eomcs.lms.dao.proxy.MemberDaoProxy;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.LessonAddCommand;
import com.eomcs.lms.handler.LessonDeleteCommand;
import com.eomcs.lms.handler.LessonDetailCommand;
import com.eomcs.lms.handler.LessonListCommand;
import com.eomcs.lms.handler.LessonUpdateCommand;
import com.eomcs.lms.handler.MemberAddCommand;
import com.eomcs.lms.handler.MemberDeleteCommand;
import com.eomcs.lms.handler.MemberDetailCommand;
import com.eomcs.lms.handler.MemberListCommand;
import com.eomcs.lms.handler.MemberUpdateCommand;
import com.eomcs.lms.prompt.Prompt;

public class ClientApp {

  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  String host;
  int port;

  HashMap<String, Command> hashMap = new HashMap<>();

  public ClientApp() {
    // 사용자가 입력한 명령어를 보관할 객체 준비
    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();

    try {
      host = prompt.inputString("서버? ");
      port = prompt.inputInt("포트? ");

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다!");
      keyboard.close();
      return;
    }

    // DAO 프록시 객체 준비
    BoardDaoProxy boardDao = new BoardDaoProxy(host, port);
    LessonDaoProxy lessonDao = new LessonDaoProxy(host, port);
    MemberDaoProxy memberDao = new MemberDaoProxy(host, port);


    // 사용자 명령을 처리할 Command 객체 준비
    hashMap.put("/board/list", new BoardListCommand(boardDao));
    hashMap.put("/board/add", new BoardAddCommand(boardDao, prompt));
    hashMap.put("/board/detail", new BoardDetailCommand(boardDao, prompt));
    hashMap.put("/board/update", new BoardUpdateCommand(boardDao, prompt));
    hashMap.put("/board/delete", new BoardDeleteCommand(boardDao, prompt));

    hashMap.put("/lesson/list", new LessonListCommand(lessonDao));
    hashMap.put("/lesson/add", new LessonAddCommand(lessonDao, prompt));
    hashMap.put("/lesson/detail", new LessonDetailCommand(lessonDao, prompt));
    hashMap.put("/lesson/update", new LessonUpdateCommand(lessonDao, prompt));
    hashMap.put("/lesson/delete", new LessonDeleteCommand(lessonDao, prompt));

    hashMap.put("/member/list", new MemberListCommand(memberDao));
    hashMap.put("/member/add", new MemberAddCommand(memberDao, prompt));
    hashMap.put("/member/detail", new MemberDetailCommand(memberDao, prompt));
    hashMap.put("/member/update", new MemberUpdateCommand(memberDao, prompt));
    hashMap.put("/member/delete", new MemberDeleteCommand(memberDao, prompt));

    hashMap.put("/server/stop", () -> {
      try(Socket socket = new Socket(host, port);
          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
        out.writeUTF("/server/stop");
        out.flush();
        System.out.println("서버: " + in.readUTF());
        System.out.println("안녕!");
      } catch (Exception e) {
        //
      }
    });
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
      Command commandHandler = hashMap.get(command);
      if (commandHandler == null) {
        System.out.println("실행할 수 없는 명령입니다.");
        return;
      }
      commandHandler.execute();
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
