// indexOf()의 사용
package com.eomcs.corelib.ex03;

public class Exam0310 {
  public static void main(String[] args) {
    String s1 = new String("aaa");
    String s2 = new String("bbb");
    String s3 = new String("ccc");
    String s4 = new String("bbb"); // s2 != s4

    ArrayList list = new ArrayList();
    list.add(s1);
    list.add(s2);
    list.add(s3);
    print(list);

    System.out.println(list.indexOf(s4));
  }

  // indexOf도 equals를 쓴다.


  static void print(ArrayList list) {
    for (int i = 0; i < list.size(); i++)
      System.out.print(list.get(i) + ", ");
    System.out.println();
  }
}


