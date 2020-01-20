// 상수 변수를 활용하여 분류 코드를 다루기
package com.eomcs.oop.ex11.c.step1;

public class Exam0110 {
  public static void main(String[] args) {
    Product p = new Product();
    p.category = 10; // 가전 / TV Product.APPLIANCE_TV;
    p.name = "울트라비전 뷰";
    p.price = 2000000;

    Product p2 = new Product();
    p2.category = 3; // 컴퓨터 / RAM
    p2.name = "삼성모듈램 4GB";
    p2.price = 80000;


    Product p3 = new Product();
    p3.category = 101; // 책 / NOVEL
    p3.name = "소설";
    p3.price = 18000;

    // 제품의 카테고리는 정수값으로 설정한다
    // 각 카테고리에 대해 정수값을 지정해두고 값을 입력할 때 사용한다.
  }
}
