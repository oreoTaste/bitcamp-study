// 생성자 레퍼런스
package com.eomcs.oop.ex12;

public class Exam0730 {
  
  static class Message {
    String name;
    
    public Message() {
      this.name = "이름없음";
    }
    
    public Message(String name) {
      this.name = name;
    }
    
    public void print() {
      System.out.println(this.name);
    }
  }
  
  static interface Factory1 {
    Message get();
  }

  static interface Factory2 {
    Message get(String name);
  }

  
  public static void main(String[] args) {
    
    Factory1 f1 = Message::new; // Factory() 생성자를 가리킨다.
    Factory2 f2 = Message::new; // Factory(String) 생성자를 가리킨다.
    
    Message msg1 = f1.get();
    msg1.print();
    
    Message msg2 = f2.get("홍길동");
    msg2.print();
  }
}


