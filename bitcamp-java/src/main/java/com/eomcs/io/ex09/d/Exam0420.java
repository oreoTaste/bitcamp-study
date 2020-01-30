// Java I/O API 사용하기 - ObjectInputStream으로 Serialize 데이터를 읽기
package com.eomcs.io.ex09.d;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Exam0420 {

  public static void main(String[] args) throws Exception {
    // data sink stream class
    FileInputStream fileIn = new FileInputStream("temp/test10.data");
    // data processing stream class (= decorator)
    BufferedInputStream bufIn = new BufferedInputStream(fileIn);
    ObjectInputStream in = new ObjectInputStream(bufIn);

    // [static 필드 (serialVersionUID)의 이해]
    // Test1
    // - Member클래스를 변경하지 않은 상태에서 데이터 읽기
    // Test2
    // - Member클래스에서 tel필드를 추가한후 데이터 읽기
    // Test3
    // - Member클래스에서 age필드를 제거한후 데이터 읽기
    
    Member member = (Member) in.readObject();

    in.close();

    //if(member.serialVersionUID() == 1280) {
      System.out.println(member);
    //} else {
    //  System.out.println("파일 형식이 다릅니다 : 실패");
    //}
  }

}






//용어 정리!
//1) Serialize (직렬화)
// - 객체(인스턴스) ===> 바이트 배열   (marshaling 이라고도 부른다.)

//2) Deserialize (객체복원)
// - 바이트 배열 ===> 객체 (인스턴스)  (unmarshaling 이라고도 부른다.)
//









