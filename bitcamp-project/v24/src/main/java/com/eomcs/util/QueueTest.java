package com.eomcs.util;

public class QueueTest {
  public static void main(String[] args) {
    Queue queue = new Queue();
    queue.offer("aaaa");
    queue.offer("bbbb");
    queue.offer("cccc");
    queue.offer("dddd");
    
    while(queue.size() > 0) {
      System.out.println(queue.poll());
    }
  }
}
