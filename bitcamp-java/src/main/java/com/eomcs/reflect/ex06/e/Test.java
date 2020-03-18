package com.eomcs.reflect.ex06.e;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
  public static void main(String[] args) {


    Origin o = (Origin) Proxy.newProxyInstance(
        Test.class.getClassLoader(),
        new Class[] {Origin.class},
        new InvocationHandler() {

          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            int cnt = ((Object[])args[0]).length;
            String[] name = new String[cnt];
            for(int i = 0; i < cnt; i++) {
              name[i] = (String) ((Object[])args[0])[i];
            }

            for(String n : name) {
              System.out.println(n + "님 반갑습니다.");
            }
            return null;
          }
        });

    o.print("1번사람", "2번사람", "3번사람");

  }

}

interface Origin {
  void print(String... person);
}