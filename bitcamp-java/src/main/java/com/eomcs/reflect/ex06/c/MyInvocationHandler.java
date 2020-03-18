package com.eomcs.reflect.ex06.c;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
 
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    
    System.out.println(method.getName() + "호출됨");
    
    if(method.getName().equalsIgnoreCase("m1")) {
      System.out.println("[m1출현]");
      return 100;
    } else {
      System.out.println("[m2출현]");
      return "hello";
    }
  }

}
