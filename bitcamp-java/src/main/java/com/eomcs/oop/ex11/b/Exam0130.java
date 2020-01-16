// 클래스의 종류 : non-static 중첩 클래스(nested class) 사용
package com.eomcs.oop.ex11.b;

import java.io.File;
import java.io.FilenameFilter;

public class Exam0130 {

  // non-static nested class = inner class
  // => 스태틱이 붙지 않은 중첩 클래스. 결국 인스턴스 멤버이다.
  // => 이 클래스는 인스턴스가 있어야 만 사용할 수 있다.
  // => 보통 인스턴스 멤버를 사용하는 중첩 클래스를 만들 때 inner class로 정의한다.
  // => 당연히 inner 클래스는 인스턴스 메서드에서 주로 사용할 것이다.
  
  // 바깥 클래스의 인스턴스를 사용하는 경우, inner class로 정의하라.
  class JavaFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
      if (name.endsWith(".java")) 
        return true; 
      return false; 
    }
  }

  public static void test1() {
    // java filter는 non-static nested class이기 때문에
    // 생성자를 호출할때 반드시 바깥 클래스의 인스턴스가 필요하다.
    // 따라서, 이메서드처럼 스태틱메서드에서 해당 클래스를 사용하려한다면
    // 오류가 발생할 것이다.
    JavaFilter javaFilter; // 레퍼런스를 선언할때는 상관없다.
    //javaFilter= new JavaFilter(); // 컴파일 오류
    
  }
  
  
  public void test2() {
    File dir = new File(".");

    // instance 메서드에서는 non-static nested class의 인스턴스를 생성할 수 있다.
    // inner class의 인스턴스를 생성한다.
    JavaFilter javaFilter;
    javaFilter = this.new JavaFilter();

    // inner class의 인스턴스를 필터로 사용하여 디렉토리 안의 목록을 추출한다.
    String[] names = dir.list(javaFilter);
    for (String name : names) {
      System.out.println(name);
    }
  }

  public static void main(String[] args) throws Exception {
    Exam0130 obj = new Exam0130();
    obj.test2();
  }

}







