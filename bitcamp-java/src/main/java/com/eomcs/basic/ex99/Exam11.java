package com.eomcs.basic.ex99;

public class Exam11 {
  public static void main(String[] args) {
    // println (출력 + 줄바꿈)
    System.out.println(100);
    System.out.println(3.14);
    System.out.println(true);
    System.out.println('가');
    System.out.println("안녕");

    System.out.println();
    // print (출력)
    System.out.print(100);
    System.out.print(3.14);
    System.out.print(true);
    System.out.print('가');
    System.out.print("안녕");
    System.out.print("\r\n");
    System.out.print("OK!\b\b");
    System.out.print("NO!");

    //printf ("형식" 값1, 값2)
    //%s 문자열 
    //%c 문자
    //%d 10진수 정수  
    //%x 16진법 정수
    System.out.printf("\n\n이름: %s", "홍길동");
    System.out.printf("\n나이: %d", 20); 

    System.out.printf("\n이름(나이): %s(%d)", "홍길동", 20);
    System.out.printf("%1$d, %1$x, %1$c", 65);
    //논리값 삽입
    System.out.printf("\n재직자 : %b", true);
    System.out.printf("\n나이 : %s", 20);

    //출력할 공간확보
    System.out.printf("\n이름 : [%s]", "홍길동");
    System.out.printf("\n이름 : [%20s]", "홍길동"); // 오른쪽 정렬
    System.out.printf("\n이름 : [%-20s]", "홍길동"); // 왼쪽 정렬

    System.out.printf("\n나이 : [%20d]", 12345);
    System.out.printf("\n나이 : [%-20d]", 12345);
    System.out.printf("\n나이 : [%020d]", 12345);
    System.out.printf("\n나이 : [%+020d] [%+020d]", 12345, -12345);

    java.util.Date today = new java.util.Date();
    today.setMonth(1);
    today.setDate(9);
    System.out.println("\n-------------------------------");
    //날짜객체에서 년,월,일,시,분,초,요일 추출하기
    System.out.printf("\n%s"      , today); // full로 나옴
    System.out.printf("\n%tY, %ty", today, today); // 년도 추출 (2019, 19)
    System.out.printf("\n%tB, %tb", today, today); // 월 추출 (07, 7)
    System.out.printf("\n%tm"     , today);        // 월 추출 
    System.out.printf("\n%td  %te", today, today); // 일 추출 (09, 9)
    System.out.printf("\n%tA  %ta", today, today); // 요일 추출 (월요일, 월)
    System.out.printf("\n%tH  %tI", today, today); // 시간 추출 (24시간 12시간 계산)
    System.out.printf("\n%tM     ", today); // 분 추출
    System.out.printf("\n%tS, %tL, %tN", today, today, today); // 초, 밀리초, 나노초 추출
    System.out.printf("\n%tp, %Tp", today, today); // 오전, 오후 추출 (소문자, 대문자)

    // 2019-12-12 12:37:45
    System.out.printf("\n%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", today);
    // 19.1.1  1:1:1
    System.out.printf("\n%ty.%1$tm.%1$te %1$tI:%1$tM:%1$tS", today);
  }
}
