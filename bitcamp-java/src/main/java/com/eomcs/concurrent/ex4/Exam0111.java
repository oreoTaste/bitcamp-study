// 스레드의 생명주기(lifecycle) - 죽은 Thread는 다시 살릴 수 없다
package com.eomcs.concurrent.ex4;

public class Exam0111 {

  public static void main(String[] args) {

    System.out.println("스레드 실행 전");
    
    Thread t = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        System.out.println("===> " + i);
      }
    });
    
    t.start();
    t.start(); // 두번실행 불가

    System.out.println("main() 종료");
    // main() 메서드의 호출이 끝나더라도 다른 스레드의 실행이 종료될 때까지
    // JVM은 종료하지 않는다.
  }

}
