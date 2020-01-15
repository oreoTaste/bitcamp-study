package com.eomcs.oop.ex09.a.before;

public class Exam01 {
  public static void main(String[] args) {
    
    BlueWorker w1 = new BlueWorker();
    WhiteWorker w2 = new WhiteWorker();
    JubuWorker w3 = new JubuWorker();

    // 각 노동자에게 일을 시키는 방법
    // 왜?
    // 메서드 호출방법이 다르기 때문에
    w1.doFight();
    w2.doZingZing();
    w3.doSsingSsing();


    // 객체에 일을 시키는 방법은 비슷한데
    // 메서드 시그니쳐가 다르기때문에
    // 호출할 때 일관성이 없다.
    
    // 방법 : 객체 사용 규칙을 정의한 인터페이스 사용!
  }
}



