// 상수 코드를 스태틱 중첩 클래스로 다루기
package com.eomcs.oop.ex11.c.step7;

public class Exam0110 {
  public static void main(String[] args) {
    Product p = new Product();
    p.category = Category.appliance.TV; // OGNL 표기법으로 지정한다.
    // OGNL (Object Graph Navigation Language)?
    // 객체안에 있는 필드를 가리킬때 점(.)을 이용하여 표기한는 방법.
    p.name = "울트라비전 뷰";
    p.price = 2000000;


    Product p2 = new Product();
    p2.category = Category.computer.RAM;
    p2.name = "삼성모듈램 4GB";
    p2.price = 80000;


    Product p3 = new Product();
    p3.category = Category.book.NOVEL;
    p3.name = "소설";
    p3.price = 18000;

  }
}
