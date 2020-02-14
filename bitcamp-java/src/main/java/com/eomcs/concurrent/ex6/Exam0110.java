// 멀티 스레드 적용 전
package com.eomcs.concurrent.ex6;

import java.util.Scanner;

public class Exam0110 {

  public static void main(String[] args) {

    class MyThread extends Thread {
      int count;
      
      public void setCount(int count) {
        this.count = count;
      }
      
      @Override
      public void run() {
        for(int i = count; i > 0; i--) {
          try {
            System.out.println("==> " + i);
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
      
    }
    
    
    MyThread t = new MyThread();
    Scanner scanner = new Scanner(System.in);
    
    while(true) {
      System.out.print("카운트? ");
      String str = scanner.nextLine();
      if(str.equalsIgnoreCase("quit")) {
        break;
      }
      
      int count = Integer.parseInt(str);
      t.setCount(count);
      t.start();
      
      
    }
    
    System.out.println("main 스레드 종료");
    
    
    
  }
}

