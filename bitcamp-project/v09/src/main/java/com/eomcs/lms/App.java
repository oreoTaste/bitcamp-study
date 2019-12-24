package com.eomcs.lms;
import java.sql.Date;

public class App {

  static class Member {
    int no;
    String name;
    String email;
    String password;
    String photo;
    String tel;
    Date registeredDate;
  }

  static class Lesson {
    int no;
    String title;
    Date date;
    int viewCount;
    String context;
    Date startDate;
    Date endDate;
    int totalHour;
    int dailyHour;
  }

  static class Board {
    int no;
    String title;
    Date date;
    int viewCount;
  }

  static java.io.InputStream inputStream = System.in;
  static java.util.Scanner scanner = new java.util.Scanner(inputStream);

  static final int SIZE = 100;
  static int lessonsCount = 0;
  static Lesson[] lessons = new Lesson[SIZE];
  static int memberCount = 0;
  static Member[] member = new Member[SIZE];
  static int boardsCount = 0;
  static Board[] boards = new Board[SIZE];

  public static void main(String[] args) {
    String command;

    do {
      System.out.println();
      System.out.print("명령> ");
      command = scanner.nextLine();

      switch (command) {
        case "/lesson/add" :
          addLesson();
          break;

        case "/lesson/list" :
          listLesson();
          break;

        case "/member/add" :
          addMember();
          break;

        case "/member/list" :
          listMember();
          break;

        case "/board/add" :
          addBoard();
          break;

        case "/board/list" :
          listBoard();
          break;

        default : 
          if(!command.equalsIgnoreCase("y"))
            System.out.println("실행할 수 없는 명령입니다.");
          break;
      }

    } while (!command.equalsIgnoreCase("quit"));
    scanner.close();
    System.out.println("...안녕!");
  }

  static void addLesson() {
    Lesson les = new Lesson();
    System.out.print("번호? ");
    les.no = scanner.nextInt();
    scanner.nextLine();
    System.out.print("수업명? ");
    les.title = scanner.nextLine();
    System.out.print("수업내용? ");
    les.context = scanner.nextLine();
    System.out.print("시작일? (형식 : 2019-01-01) ");
    les.startDate = Date.valueOf(scanner.nextLine());
    System.out.print("종료일? (형식 : 2019-01-01) ");
    les.endDate = Date.valueOf(scanner.nextLine());
    System.out.print("총수업시간? (형식: 1000) ");
    les.totalHour = scanner.nextInt();
    System.out.print("일수업시간? (형식: 8) ");
    les.dailyHour = scanner.nextInt();
    System.out.println();
    scanner.nextLine();
    
    lessons[lessonsCount++] = les;
    System.out.println("저장하였습니다.");
  }
  static void listLesson() {
    for(int i=0 ; i<lessonsCount ; i++){
      Lesson ls = lessons[i];
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n", ls.no, ls.title, ls.startDate, ls.endDate, ls.totalHour);
    }
  }

  static void addMember() {
    Member mem = new Member();
    System.out.print("번호?");
    mem.no = scanner.nextInt();
    scanner.nextLine(); // 빈칸제거
    System.out.print("이름? ");
    mem.name = scanner.nextLine();
    System.out.print("이메일? ");
    mem.email = scanner.nextLine();
    System.out.print("비밀번호? ");
    mem.password = scanner.nextLine();
    System.out.print("사진? ");
    mem.photo = scanner.nextLine();
    System.out.print("전화? ");
    mem.tel = scanner.nextLine();
    mem.registeredDate = new Date(System.currentTimeMillis());
    
    member[memberCount++] = mem;
    System.out.println("저장하였습니다.");
  }

  static void addBoard() {
    Board board = new Board();
    System.out.print("번호? ");
    board.no = scanner.nextInt();
    scanner.nextLine(); // 줄바꿈 기호 제거용
    System.out.print("내용? ");
    board.title = scanner.nextLine();
    board.date = new Date(System.currentTimeMillis());
    board.viewCount    = 0;

    boards[boardsCount++] = board;
    System.out.println("저장하였습니다.");
  }
  
  static void listMember() {
    for(int i=0 ; i<memberCount ; i++) {
      Member m = member[i];
      System.out.printf("%1$d, %2$s , %3$s       , %4$s      , %5$tH:%5$tM:%5$tS\n",
          m.no, m.name,  m.email,  m.tel , m.registeredDate );
    }
  }
  
  static void listBoard() {
    for (int i = 0; i < boardsCount; i++) {
      Board brd = boards[i];
      System.out.printf("%d, %s, %s, %d\n", 
          brd.no, brd.title, brd.date, brd.viewCount);
    }
  }
}
/*
명령>

명령> /lesson/add
번호? 1
수업명? 자바 프로젝트 실습
수업내용? 자바 프로젝트를 통한 자바 언어 활용법 익히기
시작일? 2019-01-02
종료일? 2019-05-28
총수업시간? 1000
일수업시간? 8
저장하였습니다.

명령> /lesson/list
1, 자바 프로젝트 실습     , 2019-01-02 ~ 2019-05-28, 1000
2, 자바 프로그래밍 기초    , 2019-02-01 ~ 2019-02-28,  160
3, 자바 프로그래밍 고급    , 2019-03-02 ~ 2019-03-30,  160

명령> board/view
실행할 수 없는 명령입니다.

명령> quit
안녕!
 */