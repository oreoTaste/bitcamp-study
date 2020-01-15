package com.eomcs.generic.ex03;

import java.lang.reflect.Array;

public class Exam0120 {
  
  // 제네릭 타입을 지정할때 수퍼클래스를 지정하지 않으면 모든 타입이 가능하다.
  // 즉, T자리에 어떤 타입이라도 올 수 있다.
  
  static class ArrayList<T extends Manager> {

    T[] arr;
    int index = 0;

    @SuppressWarnings("unchecked")
    public ArrayList(Class<?> clazz) {
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

    //ArrayList<Member> obj = new ArrayList<>(Member.class);
    //ArrayList<Teacher> obj = new ArrayList<>(Teacher.class);
    //ArrayList<Student> obj = new ArrayList<>(Student.class);
    ArrayList<Manager> obj1 = new ArrayList<>(Manager.class);
    ArrayList<Administrator> obj2 = new ArrayList<>(Administrator.class);

    //obj1.add(new Member());
    //obj1.add(new Student());
    //obj1.add(new Teacher());
    obj1.add(new Manager());
    obj1.add(new Administrator());

    System.out.println(obj1.get(0));
    System.out.println(obj1.get(1));
    System.out.println(obj1.get(2));
    System.out.println(obj1.get(3));
  }

}
