// 스레드 재사용 - 3단계) sleep/timeout을 활용한 스레드 재사용 2
package com.eomcs.concurrent.ex6;

import java.util.Scanner;

public class Exam0140 {

  public static void main(String[] args) {

    class MyThread extends Thread {
      int count;

      public void setCount(int count) {
        this.count = count;
        synchronized(this) {
          // synchronized block에서 지정한 객체의 사용을 기다리는 스레드에게 명령한다
          notify();
        }
      }

      @Override
      public void run() {
        synchronized(this) {
          System.out.println("스레드 시작");
          try {
            // 스레드를 사용하려면 run()메서드가 종료되지 않게 해야한다.
            while(true) {
              System.out.println("스레드 대기중..");
              wait();
              //Thread.sleep(1);
              for(int i = count; i > 0; i--) {
                System.out.println("==> " + i);
                Thread.sleep(1000);
              }
            }
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }

    }


    MyThread t = new MyThread();
    t.start();

    Scanner scanner = new Scanner(System.in);

    while(true) {
      System.out.print("카운트? ");
      String str = scanner.nextLine();
      if(str.equalsIgnoreCase("quit")) {
        break;
      }
      t.setCount(Integer.parseInt(str));
    }

    System.out.println("main 스레드 종료");
    scanner.close();

  }
}

