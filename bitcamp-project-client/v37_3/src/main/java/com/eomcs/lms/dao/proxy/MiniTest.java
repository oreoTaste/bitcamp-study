package com.eomcs.lms.dao.proxy;

class Cover {
  Inside unit;
  
  Cover(Inside unit) {
    this.unit = unit;
  }
  
  void request() {
    System.out.println("시작");
    unit.execute();
    System.out.println("끝");
  }
}

class Inside {
  String name;
  
  Inside(String name) {
    this.name = name;
  }
  
  public void execute() {
    System.out.println(this.name);
  }
  
}


public class MiniTest {
  public static void main(String[] args) {
    
    Cover helper1 = new Cover(new Inside("철수"));
    helper1.request();
    
    Cover helper2 = new Cover(new Inside("영미"));
    helper2.request();
    
  }
}
