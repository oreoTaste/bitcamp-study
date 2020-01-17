// local class - 인스턴스 멤버에 선언된 로컬클래스
package com.eomcs.oop.ex11.a;

public class Exam0360 {

  static interface Runner {
    void run();
  }

  public static void main(final String[] args) {
    final Exam0360 obj = new Exam0360();
    final Runner r = obj.createRunner("홍길동");
    r.run();
  }

  Runner createRunner(final String name) {
    class A implements Runner {

      Exam0360 outer;
      String name;

      public A(final Exam0360 obj, String name) {
        outer = obj;
        this.name = name;
      }

      @Override
      public void run() {
        System.out.printf("%s님이 달립니다.", name);
      }
    }
    return new A(this, name);
  }
}

