// 스레드 재사용 - 3단계) sleep/timeout을 활용한 스레드 재사용 2
package com.eomcs.concurrent.ex6;

import java.util.Scanner;

public class Exam0130 {

  public static void main(String[] args) {

    class MyThread extends Thread {
      boolean enable;
      int count;
      
      
      public void setCount(int count) {
        this.count = count;
        this.enable = true;
      }

      @Override
      public void run() {
        System.out.println("스레드 시작");
        try {
          // 스레드를 사용하려면 run()메서드가 종료되지 않게 해야한다.
          while(true) {
            //System.out.println("카운트 시작");
            //Thread.sleep(1000);
            System.out.print("");
            if(!enable) {
              continue;
            }
            
            for(int i = count; i > 0; i--) {
              System.out.println("==> " + i);
              Thread.sleep(1000);
            }
            this.enable = false;
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
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

      int count = Integer.parseInt(str);
      t.setCount(count);


    }

    System.out.println("main 스레드 종료");
    scanner.close();

  }
}

