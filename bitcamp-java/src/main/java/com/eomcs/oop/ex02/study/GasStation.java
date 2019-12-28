package com.eomcs.oop.ex02.study;

public class GasStation {
  static int fuel(Car c) {
    int amount = c.energy;
    c.energy = 100;
    return amount;
  }
  static void wash(Car c) {
    c.cleanLevel = 0;
  }
}
