// 상수 변수를 활용하여 분류 코드를 다루기
package com.eomcs.oop.ex11.c.step2;

public class Exam0110 {
  public static void main(String[] args) {
    Product p = new Product();
    p.category = "appliance/tv"; // 가전 / TV Product.APPLIANCE_TV;
    p.name = "울트라비전 뷰";
    p.price = 2000000;

    Product p2 = new Product();
    p2.category = "computer/ram"; // 컴퓨터 / RAM
    p2.name = "삼성모듈램 4GB";
    p2.price = 80000;


    Product p3 = new Product();
    p3.category = "book/novel"; // 책 / NOVEL
    p3.name = "소설";
    p3.price = 18000;

    // 문자열로 쓰면 좋으나, 오타가 들어가면 난감..
  }
}
