package com.eomcs.oop.ex02.study;

public class Test03 {
  public static void main(String[] args) {
    final int OIL = 1;
    final int GAS = 2;

    Car c1 = new Car();
    c1.name = "봉봉";
    c1.type = 1;
    c1.color = "빨강";
    c1.speed = 0;
    c1.energy = 100;
    c1.durability = 200;
    c1.speedUp();
    c1.speedUp();
    c1.showStatus();

    Car c2 = new Car();
    c2.name = "빙빙";
    c2.type = 2;
    c2.color = "노랑";
    c2.speed = 0;
    c2.energy = 100;
    c2.durability = 300;
    c2.speedUp();
    c2.showStatus();
    
    System.out.printf("잔류량 : %d\n", GasStation.fuel(c1));
    System.out.printf("잔류량 : %d\n", GasStation.fuel(c2));
    System.out.println();
    
    c1.showStatus();
    c2.showStatus();

    
  }
}
