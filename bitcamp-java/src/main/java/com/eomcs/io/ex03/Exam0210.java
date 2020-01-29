// Character Stream - 문자 배열 출력하기
package com.eomcs.io.ex03;

import java.io.FileWriter;

public class Exam0210 {

  public static void main(String[] args) throws Exception {
    
    FileWriter out = new FileWriter("temp/test2.txt");
    char[] chars = new char[] {'A','B','C','가','각','간','똘','똥'};
    //    JVM(UCS2) -> FileOutputSteam(UTF-8)
    // A: 00 41     -> 41
    // B: 00 42     -> 42
    // C: 00 43     -> 43
    // 가:AC 00     -> EA B0 80
    // 각:AC 01     -> EA B0 81
    // 간:AC 04     -> EA B0 82
    // 똘:B6 18     -> EB 98 98
    // 똥:B6 25     -> EB 98 A5

    out.write(chars); // 문자 배열 전체를 출력한다.
    // 당연히 UTF-16을 JVM 기본 문자 코드표에 따라 변환하여 출력한다.
    // JVM이 입출력 문자 코드표로 UTF-8을 사용한다면
    // 영어는 1바이트로 변환되어 출력될 것이고,
    // 한글은 3바이트로 변환되어 출력될 것이다.

    out.close();

    System.out.println("데이터 출력 완료!");
  }

}
