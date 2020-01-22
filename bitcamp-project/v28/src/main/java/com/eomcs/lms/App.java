package com.eomcs.lms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
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
  static ArrayList<Lesson> lessonList = new ArrayList<>();

  public static void main(String[] args) {
    // 파일에서 데이터 로딩하기
    loadLessonData();

    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> hashmap = new HashMap<>();

    LinkedList<Board> boardList = new LinkedList<>();
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

    LinkedList<Member> memberList = new LinkedList<>();
    hashmap.put("/member/add", new MemberAddCommand(prompt, memberList));
    hashmap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));
    hashmap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    hashmap.put("/member/list", new MemberListCommand(memberList));
    hashmap.put("/member/update", new MemberUpdateCommand(prompt, memberList));

    hashmap.put("/hello", new HelloCommand(prompt));
    hashmap.put("/compute/plus", new ComputePlusCommand(prompt));

    String command;

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
        } finally {

        }
      else
        System.out.println("실행할 수 없는 명령입니다.");
    }
    keyboard.close();

    // 데이터를 파일에 저장
    saveLessonData();
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
    // 데이터가 보관된 파일정보를 준비
    File file = new File("./lesson.csv");

    // 파일을 읽을 때 사용할 도구 준비
    FileReader in = null;
    Scanner dataScan = null;
    try {
      in = new FileReader(file);

      // csv 파일에서 한줄 단위로 문자열을 읽는 기능이 필요하다.
      // FileReader에는 그런 기능이 없다 -> Scanner 쓰자!
      dataScan = new Scanner(in);
      int count = 0;
      while(true) {
        try {
          // 파일에서 줄별로 읽는다.
          String line = dataScan.nextLine();
          // 한줄을 콤마로 나눈다.
          String[] data = line.split(",");
          Lesson lesson = new Lesson();
          lesson.setNo(Integer.parseInt(data[0]));
          lesson.setTitle(data[1]);
          lesson.setContext(data[2]);
          lesson.setStartDate(Date.valueOf(data[3]));
          lesson.setEndDate(Date.valueOf(data[4]));
          lesson.setTotalHour(Integer.parseInt(data[5]));
          lesson.setDailyHour(Integer.parseInt(data[6]));

          lessonList.add(lesson);
          count++;
        } catch (NoSuchElementException e) {
          break;
        }
      System.out.printf("총 %d개의 수업데이터를 로딩했습니다.\n", count);

    } catch (FileNotFoundException e) {
      System.out.println("파일 읽기 중 오류 발생 - " + e.getMessage());

    } finally {
      try {
      dataScan.close();
      } catch (Exception e) {
        // Scanner 닫다가 오류나도 무시한다.
      }
      try {
        in.close();} catch (Exception e) {
        /*in.close()를 실행하다가 오류발생한 경우 무시.*/}
    }
  }

  private static void saveLessonData() {
    // 데이터가 보관된 파일정보를 준비
    File file = new File("./lesson.csv");

    FileWriter out = null;
    try {
      // 파일을 쓸 때 사용할 도구 준비
      out = new FileWriter(file);
      int count = 0;

      // 수업목록에서 수업데이터를 꺼내 csv 형식의 문자열로 만든다.
      for(Lesson lesson : lessonList) {
        String line = String.format("%d,%s,%s,%d,%d,%d,%d\n",
            lesson.getNo(),
            lesson.getTitle(),
            lesson.getContext(),
            lesson.getStartDate(),
            lesson.getEndDate(),
            lesson.getTotalHour(),
            lesson.getDailyHour());

        out.write(line);
        count++;
      }
      System.out.printf("총 %d개의 수업데이터를 저장했습니다.\n", count);

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생 - " + e.getMessage());

    } finally {
      try {
        out.close();
      } catch (IOException e) {
        // FileWriter를 닫을때 발생하는 예외는 무시한다.
        e.printStackTrace();
      }
    }

  }
}
