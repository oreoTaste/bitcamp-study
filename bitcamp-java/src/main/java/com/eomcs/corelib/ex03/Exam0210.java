// contains() 와 equals()의 관계
package com.eomcs.corelib.ex03;

import java.util.ArrayList;

public class Exam0210 {
  public static void main(String[] args) {
    String s1 = new String("aaa");
    String s2 = new String("bbb");
    String s3 = new String("ccc");
    String s4 = new String("bbb"); // s2 != s4

    ArrayList<String> list = new ArrayList<>();
    list.add(s1);
    list.add(s2);
    list.add(s3);
    print(list);

    System.out.println(list.contains(s4));
  }

  // contains가 값을 비교할때는 equals 결과가 true인지를 비교한다.

  static void print(ArrayList<String> list) {
    for (int i = 0; i < list.size(); i++)
      System.out.print(list.get(i) + ", ");
    System.out.println();
  }
}
