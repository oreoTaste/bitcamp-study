package com.eomcs.reflect.ex06.a;

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
    
    obj.m1();
    obj.m2();
  }


}
