package com.eomcs.reflect.ex06.e;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Exam9999 {

  public static void main(String[] args) {

    Welcome w = (Welcome)Proxy.newProxyInstance(
        Exam9999.class.getClassLoader(),
        new Class[] {Welcome.class},
        new InvocationHandler() {

          @Override
          public Object invoke(Object proxy, Method method, Object[] pool) throws Throwable {
            
            Object[] args = (Object[]) pool[0];
            int cnt = args.length;
            String[] name = new String[cnt];
            for(int i = 0; i < cnt; i++) {
              name[i] = (String)args[i];
            }
            for(String n : name) {
            System.out.println(n + "님 안녕하세요");
            }
            return 0;
          }
        });
    int num = 0;
    num = w.greet("whyyy");


    //    Grammer gr = (Grammer)Proxy.newProxyInstance(
    //        Exam9999.class.getClassLoader(),
    //        new Class[] {
    //            Grammer.class
    //        },
    //        new InvocationHandler() {
    //
    //          @Override
    //          public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) {
    //            int cnt = Integer.MIN_VALUE;
    //            switch(method.getName()) {
    //
    //              case "enter":
    //                cnt = (int) args[0];
    //                for(int i = 0; i < cnt; i++) {
    //                  System.out.println();
    //                }
    //                return true;
    //
    //              case "spaceBar":
    //                cnt = (int) args[0];
    //                for(int i = 0; i < cnt; i++) {
    //                  System.out.print(" ");
    //                }
    //                return true;
    //            }
    //            return false;
    //          }
    //        });
    //
    //    System.out.print("수업시작한다!");
    //    System.out.println(gr.enter(3));
    //    System.out.print("수업끝!");
    //    gr.spaceBar(10);
    //    System.out.print("test");


  }
}
