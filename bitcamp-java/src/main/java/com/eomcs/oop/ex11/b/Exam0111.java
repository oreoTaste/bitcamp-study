// 클래스의 종류 : 패키지 멤버 클래스 사용
package com.eomcs.oop.ex11.b;

import java.io.File;

// 물론 Exam01_1 또한 패키지 멤버 클래스이다.
public class Exam0111 {

  public static void main(String[] args) throws Exception {
    // 현재 폴더에 들어있는 하위 폴더 및 파일들의 이름을 출력하라.
    
    // => File class 를 이용하여 현재 폴더의 정보를 저장.
    // => File class는 OS의 기능을 이용하여 폴더 및 파일 정보를 다루는 일을 한다.
    File dir = new File(".");

    // 파일이름이 .java로 끝나는 파일만 꺼낸다.
    // filter 구현체이다.
    // File.list()를 호출할때 파라미터로 넘겨준다
    // 그러면 list() 메서드가 리턴할 이름을 준비할때 filter의 결정에 따라
    // 리턴 목록에 포함시킬지 말지 결정한다.
    JavaFilter javaFilter = new JavaFilter();

    // 패키지 멤버 클래스의 인스턴스를 필터로 사용하여 디렉토리 안의 목록을 추출한다.
    String[] names = dir.list(javaFilter);
    for (String name : names) {
      System.out.println(name);
    }

  }

}







