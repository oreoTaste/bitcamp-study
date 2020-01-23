package com.eomcs.lms;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.handler.BoardAddCommand;
import com.eomcs.lms.handler.BoardDeleteCommand;
import com.eomcs.lms.handler.BoardDetailCommand;
import com.eomcs.lms.handler.BoardListCommand;
import com.eomcs.lms.handler.BoardUpdateCommand;
import com.eomcs.lms.handler.Command;
import com.eomcs.lms.handler.ComputePlusCommand;
import com.eomcs.lms.handler.HelloCommand;
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
import com.eomcs.util.Prompt;

public class App {
  static java.io.InputStream inputStream = System.in;
  static java.util.Scanner keyboard = new java.util.Scanner(inputStream);
  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();
  static LinkedList<Board> boardList = new LinkedList<>();
  static ArrayList<Lesson> lessonList = new ArrayList<>();
  static LinkedList<Member> memberList = new LinkedList<>();

  public static void main(String[] args) {
    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> hashmap = new HashMap<>();

    hashmap.put("/board/add", new BoardAddCommand(prompt, boardList));
    hashmap.put("/board/delete", new BoardDeleteCommand(prompt, boardList));
    hashmap.put("/board/detail", new BoardDetailCommand(prompt, boardList));
    hashmap.put("/board/list", new BoardListCommand(boardList));
    hashmap.put("/board/update", new BoardUpdateCommand(prompt, boardList));

    hashmap.put("/lesson/add", new LessonAddCommand(prompt, lessonList));
    hashmap.put("/lesson/delete", new LessonDeleteCommand(prompt, lessonList));
    hashmap.put("/lesson/detail", new LessonDetailCommand(prompt, lessonList));
    hashmap.put("/lesson/list", new LessonListCommand(lessonList));
    hashmap.put("/lesson/update", new LessonUpdateCommand(prompt, lessonList));

    hashmap.put("/member/add", new MemberAddCommand(prompt, memberList));
    hashmap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));
    hashmap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    hashmap.put("/member/list", new MemberListCommand(memberList));
    hashmap.put("/member/update", new MemberUpdateCommand(prompt, memberList));

    hashmap.put("/hello", new HelloCommand(prompt));
    hashmap.put("/compute/plus", new ComputePlusCommand(prompt));

    String command;
    loadLessonData();
    loadMemberData();
    loadBoardData();

    while (true) {
      System.out.println();
      System.out.print("명령> ");

      command = keyboard.nextLine();
      commandStack.push(command);
      commandQueue.offer(command);

      if (command.length() == 0)
        continue;
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
      if (commandHandler != null)
        try {
          commandHandler.execute();
        } catch(Exception e) {
          System.out.printf("명령어 실행 중 오류발생 : %s\n", e.getMessage());
        }
      else
        System.out.println("실행할 수 없는 명령입니다.");
    }
    keyboard.close();
    saveLessonData();
    saveMemberData();
    saveBoardData();
  }


  private static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;

    while (iterator.hasNext()) {
      System.out.println(iterator.next());

      if (++count % 5 == 0) {
        System.out.print(": (중지하고 싶으면 q)");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q"))
          break;
      }
    }
  }

  private static void loadLessonData() {
    File file = new File("./lesson.csv");
    Scanner scanner = null;
    try (FileReader in = new FileReader(file)) {
      int count = 0;
      scanner = new Scanner(in);

      while (true)
        try {
          lessonList.add(Lesson.valueOf(scanner.nextLine().split(",")));
          count++;
        } catch (Exception e) {
          break;
        }
      System.out.printf("총 %d개 레슨 로딩완료\n", count);
    } catch(IOException e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  private static void saveLessonData() {
    File file = new File("./lesson.csv");

    try (FileWriter out = new FileWriter(file)) {
      int count = 0;

      for(Lesson lesson : lessonList) {
        out.write(lesson.toCsvString());
        count++;
      }
      System.out.printf("총 %d개 레슨 저장완료\n", count);
    } catch(IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }

  }

  private static void loadMemberData() {
    File file = new File("./member.csv");

    try (FileReader in = new FileReader(file)) {
      int count = 0;
      Scanner scanner = new Scanner(in);
      
      while(true)
        try {
          memberList.add(Member.valueOf(scanner.nextLine().split(",")));
          count++;
        } catch (Exception e) {
          break;
        }
      System.out.printf("총 %d개 멤버 로딩완료\n", count);

    } catch(Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  private static void saveMemberData() {

    File file = new File("./member.csv");

    try (FileWriter out = new FileWriter(file)) {
      int count = 0;
      for(Member member : memberList) {
        out.write(member.toScvString());
        count++;
      }
      System.out.printf("총 %d개 멤버 저장완료\n", count);

    }catch (IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }
  }

  private static void loadBoardData() {
    File file = new File("./board.csv");

    try (FileReader in = new FileReader(file)) {
      int count = 0;
      Scanner scanner = new Scanner(in);

      while(true)
        try {
          boardList.add(Board.valueOf(scanner.nextLine().split(",")));
          count++;
        } catch(Exception e) {
          break;
        }
      System.out.printf("총 %d개 게시글 로딩완료\n", count);

    } catch (Exception e) {
      System.out.println("로딩 실패 : " + e.getMessage());
    }
  }

  private static void saveBoardData() {
    File file = new File("./board.csv");

    try (FileWriter out = new FileWriter(file)) {
      int count = 0;

      for(Board board : boardList) {
        out.write(board.toCsvString());
        count++;
      }

      System.out.printf("총 %d개 게시글 저장완료\n", count);
    } catch (IOException e) {
      System.out.println("저장 실패 : " + e.getMessage());
    }
  }



}


