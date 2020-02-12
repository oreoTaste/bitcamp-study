// 멀티 스레드 적용 후
package com.eomcs.concurrent.ex1;

public class Exam0120 {

  static class MyThread extends Thread {
    @Override
    public void run() {
      // 기존 실행 흐름과 분리하여 따로 실행시킬 코드를
      // 이 메서드에 둔다.
      for (int i = 0; i < 1000; i++) {
        System.out.println("==> " + i);
      }
    }
  }

  public static void main(String[] args) {
    // 코드 실행 라인을 새로 만들어 따로 실행한다.
    // 스레드는 비동기로 동작한다.
    // 스레드에 작업을 시킨 후, 그 스레드가 작업이 끝날 때까지 기다리지 않고 즉시 리턴한다.
    // 따라서 스레드 작업과 main()의 코드가 병행(concurrent)으로 실행한다.
    new MyThread().start();

    for (int i = 0; i < 1000; i++) {
      System.out.println(i + " < ");
    }
    
    for (int i = 0; i < 1000; i++) {
      System.out.println("!!!! " + i);
    }
    
  }

}
// main() 메서드를 실행하는 기본 실행 흐름에서 새로운 실행 흐름으로 분기하고 싶다면,
// Thread 클래스에 분기해서 실행할 코드를 담으면 된다.
// 그러면 두 개의 실행 흐름이 서로 왔다 갔다 하면서 실행된다.

// Multi-tasking
// 한개의 CPU가 여러 코드를 동시에 실행하는것
// 실제로는 일정한 시간을 쪼개 코드를 번갈아 실행한다.

// CPU를 쪼개는 전략
// ##CPU scheduling = process scheduling
// 1) Round Robin 방식 (Windows OS) : 일정시간을 돌아가면서 실행
// 2) Priority 방식 (Unix, Linux) : 우선순위가 높은 코드에 더 많은 실행시간을 배정
// -> 우선순위가 낮은 경우 아예 CPU배정을 못받기도 함
// -> 해결방법 : 배정받지 못할때마다 aging기법(즉, 우선순위를 높힘)을 사용한다.

// ##Context Switching
// CPU의 실행시간을 쪼개 실행할때, 실행위치를 저장/로딩하는 과정.

// ##Thread
// 실 (하나의 실행흐름)



