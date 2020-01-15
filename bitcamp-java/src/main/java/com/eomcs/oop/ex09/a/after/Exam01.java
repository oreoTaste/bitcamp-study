// Worker 구현체 사용
package com.eomcs.oop.ex09.a.after;

public class Exam01 {
  public static void main(String[] args) {
    // 같은 사용규칙에 따라 만든 클래스는 
    // 인터페이스 reference에 그 객체 주소를 저장할 수 있어 편리하다.

    Worker w1 = new BlueWorker();
    Worker w2 = new WhiteWorker();
    Worker w3 = new JubuWorker();

    // 인터페이스를 구현하지 않은 클래스의 인스턴스 주소를 저장할 수 없다. 
    //Worker w2 = new String();// 컴파일 오류!


    // 인터페이스 구현체 호출
    w1.execute();
    w2.execute();
    w3.execute();

    // 역할?
    // caller : Exam01
    // callee : BlueWorker, WhiteWorker, JubuWorker
    // 규칙: Worker

  }
}



