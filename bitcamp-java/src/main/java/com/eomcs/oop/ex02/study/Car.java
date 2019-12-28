package com.eomcs.oop.ex02.study;

public class Car {
  String name;
  int type;
  String color;
  int speed;
  int energy;
  int durability;
  int cleanLevel;

  void speedUp () {
    if(this.speed >= 100) {
      energy -= 5;
      return;
    } else {
      this.speed += 10;
      energy -= 5;
    }
  }

  void speedDown () {
    if(this.speed <= 0) {
      energy -= 5;
      return;
    }
    else {
      this.speed -= 10;
      energy -= 5;
    }
  }
  
  void showStatus () {
    System.out.printf("%s의 차량정보\n", this.name);
    System.out.printf("-연료 : %d \n", this.energy);
    System.out.printf("-속도 : %d \n", this.speed);
    System.out.println();
  }
  
  int comparePower(Car c) {
    if(this.durability > c.durability) return -1;
    else if (this.durability == c.durability) return 0;
    else if (this.durability < c.durability) return 1;
    return 2;
  }
}
