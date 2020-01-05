package com.eomcs.lms;
import java.sql.Date;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    final int SIZE = 100;

    class Lesson {
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

    Lesson[] lessons = new Lesson[SIZE];
    int count = 0;

    java.io.InputStream inputStream = System.in;
    java.util.Scanner scanner = new java.util.Scanner(inputStream);

    for(int i=0 ; ; i++){
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
      count++;
      lessons[i] = les;
      System.out.print("계속입력하시겠습니까?(y/n) ");
      String check = scanner.nextLine();
      if(check.equals("Y")||check.equals("y")) continue;
      else if(check.equals("N")||check.equals("n")) break;
    } scanner.close();

    for(int i=0 ; i<count ; i++){
      Lesson les = lessons[i];
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n", les.no, les.title, les.startDate, les.endDate, les.totalHour);
    }

  }
}
/*
번호? 1
수업명? 자바 프로젝트 실습
수업내용? 자바 프로젝트를 통한 자바 언어 활용법 익히기
시작일? 2019-01-02
종료일? 2019-05-28
총수업시간? 1000
일수업시간? 8

계속 입력하시겠습니까?(Y/n) y

번호? 2
수업명? 자바 프로그래밍 기초
수업내용? 자바 언어 기초 문법을 학습하기
시작일? 2019-02-01
종료일? 2019-02-28
총수업시간? 160
일수업시간? 8

계속 입력하시겠습니까?(Y/n) y

번호? 3
수업명? 자바 프로그래밍 고급
수업내용? 디자인 패턴과 리랙토링 기법 학습하기
시작일? 2019-03-02
종료일? 2019-03-30
총수업시간? 160
일수업시간? 8

계속 입력하시겠습니까?(Y/n) n

1, 자바 프로젝트 실습     , 2019-01-02 ~ 2019-05-28, 1000
2, 자바 프로그래밍 기초    , 2019-02-01 ~ 2019-02-28,  160
3, 자바 프로그래밍 고급    , 2019-03-02 ~ 2019-03-30,  160
 */