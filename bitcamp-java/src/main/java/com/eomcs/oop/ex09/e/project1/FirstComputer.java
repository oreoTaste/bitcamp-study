package com.eomcs.oop.ex09.e.project1;

import com.eomcs.oop.ex09.e.Computer;

public class FirstComputer implements Computer {
  @Override
  public void compute() {
    System.out.println("단순히 계산을 수행한다!");
  }

  public static void main(String[] args) {
    FirstComputer fc = new FirstComputer();
    fc.touch();
  }
}
