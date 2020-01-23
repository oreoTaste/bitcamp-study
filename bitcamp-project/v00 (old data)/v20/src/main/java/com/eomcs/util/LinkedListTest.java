package com.eomcs.util;

public class LinkedListTest {
  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<>();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");

    list.set(2, "wow");
    print(list);
  }
  
  static void print(LinkedList<String> list) {
    String[] arr = list.toArray(new String[0]);
    for(String value : arr) {
      System.out.println(value);
    }
  }
  
}
