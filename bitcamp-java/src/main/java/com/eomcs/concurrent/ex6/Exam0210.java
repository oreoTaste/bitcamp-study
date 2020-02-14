// 스레드 재사용) Pooling 기법을 이용하여 객체 재사용하기
package com.eomcs.concurrent.ex6;

import java.util.ArrayList;
import java.util.Scanner;


public class Exam0210 {

  interface ThreadPool {
    Thread get();
    void add(Thread thread);
  }

  static class MyThread extends Thread {
    ThreadPool pool;
    int count;

    public MyThread(String name, ThreadPool pool) {
      super(name);
      this.pool = pool;
    }

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
        try {

          while(true) {
            wait();

            for(int i = count; i > 0; i--) {
              System.out.printf("[%s] : %d\n", this.getName(), i);
              Thread.sleep(1500);
            }
            // 스레드 작업이 끝났으면 스레드풀로 돌아가야한다.
            pool.add(this);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  static class MyThreadPool implements ThreadPool {
    ArrayList<MyThread> list = new ArrayList<>();

    public MyThreadPool() {
      MyThread t1 = new MyThread("1번 스레드", this);
      t1.start();
      list.add(t1);

      MyThread t2 = new MyThread("2번 스레드", this);
      t2.start();
      list.add(t2);

      MyThread t3 = new MyThread("3번 스레드", this);
      t3.start();
      list.add(t3);
    }

    @Override
    public MyThread get() {
      if(list.size() > 0)
        return list.remove(0);
      return null;
    }


    @Override
    public void add(Thread thread) {
      list.add((MyThread)thread);
    }
  }


  public static void main(String[] args) {
    MyThreadPool threadPool = new MyThreadPool();

    Scanner scanner = new Scanner(System.in);

    while(true) {
      System.out.print("카운트? ");
      String str = scanner.nextLine();
      if(str.equalsIgnoreCase("quit")) {
        break;
      }
      int count = Integer.parseInt(str);
      // 스레드풀에서 스레드를 한개 꺼낸다.
      MyThread t = threadPool.get();

      if(t == null) {
        System.out.println("스레드가 없습니다.");
        continue;
      }

      t.setCount(count);

    }

    System.out.println("main 스레드 종료");
    scanner.close();

  }
}

