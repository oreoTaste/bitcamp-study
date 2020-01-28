// 활용 - 지정한 폴더 및 그 하위 폴더까지 모두 검색하여 내용을 출력하라
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0720 {

  public static void main(String[] args) throws Exception {

    // 결과 예시)
    // src/
    //   main/
    //     java/
    //       com/
    //         Hello.java
    //         Hello2.java
    // build.gradle
    // settings.gradle
    // Hello.java

    File dir = new File("temp");
    System.out.println(dir.getCanonicalPath());
    delete(dir);
  }

  private static void delete(File dir) {
    
    if(dir.isDirectory()) {
      File[] files = dir.listFiles();
      
      for(File file : files) {
        delete(file);
      }
    }
    dir.delete();
  }
}







