package com.eomcs.lms;
import java.sql.Date;
import java.util.Scanner;

public class App {
  static final int size = 100;
  static Board[] boards = new Board[size];
  static int count = 0;

  public static void main(String[] args) {
    // 키보드에서 사용자가 입력한 값을 읽어 문자열이나 정수, 부동소수점으로 리턴하는 역할
   
    inputFromUser();
    printout();

  }

  static void inputFromUser(){
    java.io.InputStream inputStream = System.in;
    java.util.Scanner scanner = new java.util.Scanner(inputStream);

    for(int i=0 ; ; i++){
      Board b = new Board();
      System.out.print("번호? ");
      b.no = scanner.nextInt();
      scanner.nextLine();
      System.out.print("수업명? ");
      b.title = scanner.nextLine();
      System.out.print("수업내용? ");
      b.context = scanner.nextLine();
      System.out.print("시작일? (형식 : 2019-01-01) ");
      b.startDate = Date.valueOf(scanner.nextLine());
      System.out.print("종료일? (형식 : 2019-01-01) ");
      b.endDate = Date.valueOf(scanner.nextLine());
      System.out.print("총수업시간? (형식: 1000) ");
      b.totalHour = scanner.nextInt();
      System.out.print("일수업시간? (형식: 8) ");
      b.dailyHour = scanner.nextInt();
      System.out.println();
      scanner.nextLine();
      count++;
      boards[i] = b;
      System.out.print("계속입력하시겠습니까?(y/n) ");
      String check = scanner.nextLine();
        if(check.equals("Y")||check.equals("y")) continue;
        else if(check.equals("N")||check.equals("n")) break;
      } scanner.close();
  }


  static void printout(){
    for(int i=0 ; i<count ; i++){
      Board b = boards[i];
      System.out.printf("%d, %s     , %tF ~ %tF, %d\n", b.no, b.title, b.startDate, b.endDate, b.totalHour);
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