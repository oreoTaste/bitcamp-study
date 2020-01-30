// Java I/O API 사용하기 - ObjectInputStream으로 Serialize 데이터를 읽기
package com.eomcs.io.ex09.c;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Exam0320 {

  public static void main(String[] args) throws Exception {
    // data sink stream class
    FileInputStream fileIn = new FileInputStream("temp/test10.data");
    // data processing stream class (= decorator)
    BufferedInputStream bufIn = new BufferedInputStream(fileIn);
    ObjectInputStream in = new ObjectInputStream(bufIn);

    // Serialize로 출력된 데이터를 읽어 다시 원래의 객체로 만들기
    Member member = (Member) in.readObject();

    in.close();

    System.out.println(member);
  }

}

// Test1
// Exam0310에서 Member객체를 출력한다.
// Exam0320에서 Member객체를 읽는다.

// Test2
// Exam0310에서 Member객체를 출력한다.
// Member 클래스에 인스턴스 필드 'tel'을 추가하고 toString()에서 tel을 추가한다.
// Exam0320에서 Member객체를 읽는다.
// => 오류발생 (시리얼 번호가 다르다)
// => 이유?
// deserialize할때 (readObject()를 통해 바이트배열을 읽어 객체를 생성할때)
// 같은 클래스 인지 검사하는데, 다르다고 나타남.
// => 검사방법?
// 클래스에 내장된 변수 (static serialVersionUID)의 값을 비교한다.





//용어 정리!
//1) Serialize (직렬화)
// - 객체(인스턴스) ===> 바이트 배열   (marshaling 이라고도 부른다.)

//2) Deserialize (객체복원)
// - 바이트 배열 ===> 객체 (인스턴스)  (unmarshaling 이라고도 부른다.)
//









