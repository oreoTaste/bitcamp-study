// 생성자 활용 예 - java.util.Calendar 클래스의 생성자
package com.eomcs.oop.ex04;

import java.util.Calendar;

public class Exam0130 {
  public static void main(String[] args) throws Exception {
    // 생성자가 있다하더라도 접근 권한이 없다면,
    // 생성자를 호출할 수 없다.
    // 따라서 인스턴스를 생성할 수 없다.
    // Calendar c = new Calendar(); // 컴파일 오류!
    // 팩토리 메서드 설계 기법 (=팩토리 매서드 디자인 패턴)
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    System.out.println(c1 == c2);
    
    // single tone 디자인패턴!
    // 객체를 여러개 만들지 못하게 한다
    // (메모리 절약 및 객체생성이 복잡할때)
  }
}














