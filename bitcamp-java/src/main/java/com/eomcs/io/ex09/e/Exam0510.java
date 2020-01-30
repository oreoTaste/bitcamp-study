// Java I/O API 사용하기 - serialize와 transient 변경자
package com.eomcs.io.ex09.e;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Exam0510 {

  public static void main(String[] args) throws Exception {
    FileOutputStream fileOut = new FileOutputStream("temp/test11.data");
    BufferedOutputStream bufOut = new BufferedOutputStream(fileOut);
    ObjectOutputStream out = new ObjectOutputStream(bufOut);

    Score s = new Score();
    s.name = "홍길동";
    s.kor = 99;
    s.eng = 80;
    s.math = 92;
    // s.compute();
    // serialize할때 transient가 붙은 값은 저장이 되지 않는다.

    out.writeObject(s);

    out.close();
  }

}

// 용어 정리!
// Serialize   : 객체 ===> 바이트 배열   (marshalling 이라고도 부른다.)
// Deserialize : 바이트 배열 ===> 객체   (unmarshalling 이라고도 부른다.)
//









