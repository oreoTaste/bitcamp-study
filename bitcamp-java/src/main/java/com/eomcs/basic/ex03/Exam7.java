package com.eomcs.basic.ex03;

import java.math.BigDecimal;

public class Exam7 {
  public static void main(String[] args){
    //escape 문자 (문자를 제어하는 기능을 가진 문자)
    //\n \r \f 
    System.out.println("HelloWorld!");
    System.out.println("Hello\nWorld!"); //줄바꾸기
    System.out.println("Hello\tWorld!"); //탭하기
    System.out.print("testeee");
    System.out.println("Hellooooooo wow\rzzz"); //커서를 맨처음으로 돌리기)
    System.out.println("Hello world,\b\b\bwow!"); // 커서를 한칸앞으로 돌리기
    System.out.println("Hello world,\fwow!"); // formfeed 추가시키는 문자
    System.out.println("He told me this \"I like it!\" haha"); // ""안에 ""를 넣고 싶으면 \" 이렇게 하면 된다.
    System.out.println("He told me this \'I like it!\' haha"); // ""안에 ''는 그냥 써도 된다
    System.out.println('\'');                                   // ''안에 '를 쓰고싶으면 \' 이렇게 하면 된다
    System.out.println("C:\\Users\\user\\git"); // '\'를 출력하고 싶으면 \\ 이렇게 두번쓰면 된다
  }
}

// 줄바꿈코드
// Carriage Return (CR) : 0d \r
// Line Feed(LF) : 0a  \n

// 윈도우에서는 줄바꿈을 표시하기 위해
// CRLF 2바이트 코드를 삽입한다.\r\n

// UNIX에서는 줄바꿈을 표시하기위해
// LF 1바이트 코드를 삽입한다.\n