package com.eomcs.concurrent.ex6;

import java.util.LinkedList;
import java.util.List;

public class ThreadPoolTest {
  public static void main(String[] args) throws InterruptedException {

    Thread t1 = new Thread(new MyThread(1));
    ThreadPool p1 = new ThreadPool();
    p1.add(t1);
    p1.show(t1);
    p1.reuse(t1);
    System.out.println("재시도");
    //p1.show(t1);
  }



}

class ThreadPool {
  List<Runnable> list = new LinkedList<>();
  int count = 0;

  void add(Runnable thread) {
    list.add(thread);
  }

  void show(Runnable thread) throws InterruptedException {
    (new Thread(list.remove(0))).start();
  }

  void reuse(Runnable thread) {
    synchronized(this){
      thread.notify();
    }

  }


}


class MyThread implements Runnable {
  int num;

  MyThread(int num) {
    this.num = num;
  }

  @Override
  public void run() {
    synchronized(this){
      try {
        this.wait();

        while(true) {
          System.out.println(num + "번째 스레드 가동");
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}

