// FileInputStream -> DataInputStream
package com.eomcs.io.ex08;

import java.io.FileInputStream;

public class Exam0320 {

  public static void main(String[] args) throws Exception {
    //첫번째 부품
    FileInputStream fileIn = new FileInputStream("temp/test7.data");
    // 첫번째 부품에 다른 부품 연결
    // FileInputStream 객체에 String,int,boolean 값을 읽는
    // 플러그인/장신구/보조장치(decorator)를 장착한다.
    DataInputStream in = new DataInputStream(fileIn);

    Member member = new Member();

    long start = System.currentTimeMillis();
    // 플러그인을 통해 String, int, boolean 값을 읽는다.
    for(int i=0 ; i <100000 ;i++) {
    member.name = in.readUTF();
    member.age = in.readInt();
    member.gender = in.readBoolean();
    }

    in.close();
    long end = System.currentTimeMillis();

    System.out.printf("%s\n", member);
    System.out.println(end - start);
  }
}






