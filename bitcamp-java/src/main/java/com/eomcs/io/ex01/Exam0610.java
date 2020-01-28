// 디렉토리에 들어있는 파일(디렉토리) 목록을 꺼낼 때 필터 적용하기
package com.eomcs.io.ex01;

import java.io.File;
import java.io.FilenameFilter;

public class Exam0610 {


  public static void main(String[] args) throws Exception {
    class JavaFilter implements FilenameFilter {
      @Override
      public boolean accept(File dir, String name) {
        // 이 메서드는 list()에서 호출된다.
        // 현재폴더만 찾는거지. 하위폴더를 뒤지지는 않는다.
        //if(name.contains("a"))
          if (name.endsWith(".java"))
          return true; // 조회 결과에 포함시켜라!
        return false; // 조회 결과에서 제외하라!
      }
    }


    File dir = new File(".");
    // => 확장자가 .java 인 파일의 이름만 추출하기
    // 1) 필터 준비
    JavaFilter javaFilter = new JavaFilter();

    // 2) 필터를 사용하여 디렉토리의 목록을 가져오기
    String[] names = dir.list(javaFilter);

    for (String name : names)
      System.out.println(name);

  }

  // 문제점 : temp.java는 디렉토리이다. (자바파일이 아님)
}







