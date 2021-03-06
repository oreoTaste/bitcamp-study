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
  java.io.InputStream inputStream = System.in;
  java.util.Scanner keyboard = new java.util.Scanner(inputStream);
  Prompt prompt = new Prompt(keyboard);
  
  public void service() {

    String serverAddr = null;
    int port = 0;
    
    try{
      serverAddr = prompt.inputString("서버? ");
      port = prompt.inputInt("포트? ");
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage());
      keyboard.close();
      return;
    }
    try(
        Socket socket = new Socket(serverAddr, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
      System.out.print("서버와 연결완료");
      
      processCommand(out, in);

      System.out.println("서버와 연결종료");
    } catch (Exception e) {
      System.out.println("예외발생 : " + e.getMessage());
    }
    keyboard.close();
    
  }
  
  private void processCommand(ObjectOutputStream out, ObjectInputStream in) {
    Deque<String> commandStack = new ArrayDeque<>();
    Queue<String> commandQueue = new LinkedList<>();
    String command;
    HashMap<String, Command> hashmap = new HashMap<>();
    
    // Dao프록시 객체 생성
    BoardDaoProxy boardDao = new BoardDaoProxy(in, out);
    LessonDaoProxy lessonDao = new LessonDaoProxy(in, out);
    MemberDaoProxy memberDao = new MemberDaoProxy(in, out);
    
    hashmap.put("/board/list", new BoardListCommand(boardDao));
    hashmap.put("/board/add", new BoardAddCommand(boardDao, prompt));
    hashmap.put("/board/detail", new BoardDetailCommand(boardDao, prompt));
    hashmap.put("/board/update", new BoardUpdateCommand(boardDao, prompt));
    hashmap.put("/board/delete", new BoardDeleteCommand(boardDao, prompt));
     
    hashmap.put("/member/list", new MemberListCommand(memberDao));
    hashmap.put("/member/add", new MemberAddCommand(memberDao, prompt));
    hashmap.put("/member/detail", new MemberDetailCommand(memberDao, prompt));
    hashmap.put("/member/update", new MemberUpdateCommand(memberDao, prompt));
    hashmap.put("/member/delete", new MemberDeleteCommand(memberDao, prompt));

    hashmap.put("/lesson/list", new LessonListCommand(lessonDao));
    hashmap.put("/lesson/add", new LessonAddCommand(lessonDao, prompt));
    hashmap.put("/lesson/detail", new LessonDetailCommand(lessonDao, prompt));
    hashmap.put("/lesson/update", new LessonUpdateCommand(lessonDao, prompt));
    hashmap.put("/lesson/delete", new LessonDeleteCommand(lessonDao, prompt));

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
