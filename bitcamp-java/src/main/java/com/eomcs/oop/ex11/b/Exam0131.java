// 클래스의 종류 : non-static 중첩 클래스(nested class) 사용
package com.eomcs.oop.ex11.b;

import java.io.File;
import java.io.FilenameFilter;

public class Exam0131 {

  // non-static nested class = inner class
  class JavaFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
      if (name.endsWith(".java")) 
        return true; 
      return false; 
    }
  }


  public static void main(String[] args) throws Exception {
    File dir = new File(".");
    JavaFilter javaFilter;
    
    // 스태틱 메서드에서는 인스턴스 멤버를 사용하려면
    // 인스턴스를 생성해야한다.
    
    Exam0131 obj = new Exam0131();
    
    javaFilter = obj.new JavaFilter();
    String[] names = dir.list(javaFilter);
    for (String name : names) {
      System.out.println(name);


    }

  }



}



