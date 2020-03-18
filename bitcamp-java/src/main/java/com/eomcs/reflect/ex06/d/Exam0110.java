package com.eomcs.reflect.ex06.d;

import java.lang.reflect.Proxy;

public class Exam0110 {

  public static void main(String[] args) throws ClassNotFoundException {

    MyInterface obj = (MyInterface) Proxy.newProxyInstance(
        Exam0110.class.getClassLoader(), //클래스를 로딩시킬 놈
        new Class[] {
            MyInterface.class
        }, // 자동생성할 클래스가 구현해야하는 인터페이스 목록
        new MyInvocationHandler()
        );
    
    System.out.println(obj.m1(10, 20));
    System.out.println("======================================");
    System.out.println(obj.m2("까마귀", 10));
  }


}
