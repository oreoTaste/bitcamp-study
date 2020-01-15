package com.eomcs.generic.ex03;

import java.lang.reflect.Array;

public class Exam0110 {
  
  // 제네릭 타입을 지정할때 수퍼클래스를 지정하지 않으면 모든 타입이 가능하다.
  // 즉, T자리에 어떤 타입이라도 올 수 있다.
  
  static class ArrayList<T> {

    T[] arr;
    int index = 0;

    @SuppressWarnings("unchecked")
    public ArrayList(Class<?> clazz) {
      // this.arr = new T[100];
      this.arr = (T[])Array.newInstance(clazz, 10);
    }

    public void add(T v) {
      arr[index++] = v;
    }

    public T get(int index) {
      return arr[index];
    }
  }

  public static void main(String[] args) {

    ArrayList<Member> obj = new ArrayList<>(Member.class);
    obj.add(new Member());
    obj.add(new Student());
    obj.add(new Teacher());
    obj.add(new Manager());

    System.out.println(obj.get(0));
    System.out.println(obj.get(1));
    System.out.println(obj.get(2));
    System.out.println(obj.get(3));
  }

}
