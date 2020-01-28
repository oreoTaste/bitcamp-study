// 익명클래스로 FileFilter만들기
package com.eomcs.io.ex01;

import java.io.File;
import java.io.FileFilter;

public class Exam0630 {


  public static void main(String[] args) throws Exception {

    File dir = new File(".");

    // => 확장자가 .java 인 파일의 이름만 추출하기
    // 2) 필터를 사용하여 디렉토리의 목록을 가져오기
    File[] files = dir.listFiles(new FileFilter() {

      @Override
      public boolean accept(File pathname) {
        if(pathname.isFile() && pathname.getName().endsWith(".java"))
          return true;
        return false;
      }
    });

    for (File file : files)
      System.out.printf("%s %12d %s\n",
          file.isDirectory() ? "d" : "-",
              file.length(),
              file.getName());

  }

}







