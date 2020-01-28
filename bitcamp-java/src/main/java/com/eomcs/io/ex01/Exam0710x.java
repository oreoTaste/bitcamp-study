// 활용 - 지정한 폴더 및 그 하위 폴더까지 모두 검색하여 내용을 출력하라
package com.eomcs.io.ex01;

import java.io.File;

public class Exam0710x {

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

    File dir = new File(".");
    System.out.println(dir.getCanonicalPath());
    printList(dir, 1);
  }

  private static void printList(File dir, int level) {
    
    File[] files = dir.listFiles();
    for(File file : files) {
      
      printIndent(level);
      
      if(file.isDirectory() && !file.isHidden()) {
        System.out.printf("%s/\n",file.getName());
        printList(file, level+1);
      } else {
        System.out.printf("ㄴㅡ%s\n",file.getName());
      }
      
    }
    
  }

  private static void printIndent(int level) {
    for(int i = 0; i < level; i++) {
      System.out.print(" ");
    }
  }


}







