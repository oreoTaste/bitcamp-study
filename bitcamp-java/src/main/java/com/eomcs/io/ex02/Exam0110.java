// Byte Stream - 바이트 단위로 출력하기
package com.eomcs.io.ex02;

import java.io.File;
import java.io.FileOutputStream;

public class Exam0110 {

  public static void main(String[] args) throws Exception {
    // 1) 파일로 데이터를 출력하는 객체를 준비한다.
    // new FileOutputStream("경로");
    // 기존에 같은 파일이 있으면 덮어쓴다.(기존 파일 삭제)
    // 파일 경로가 절대경로가 아니면(즉, C:\로 시작하지 않으면)
    // 현재 디렉토리가 기준이 된다.
    File file = new File("temp/test1.data");
    FileOutputStream out = new FileOutputStream(file.getPath());
    file.mkdirs();
    
    // 2) 1바이트를 출력한다.
    // => int 값을 아규먼트로 넘기더라도 맨 마지막 1바이트만 출력한다.
    out.write(0x7a6b5c4d); // 출력하는 값은 0x4d 이다.

    // 3) 출력 도구를 닫는다.
    // => 항상 입출력 도구를 사용한 후 닫아야 한다.
    // => 어떤 입출력 도구는 닫지 않으면 버퍼(임시메모리)에 남아있는 데이터가
    //    출력되지 않고 버려진다.
    out.close();

    System.out.println("데이터 출력 완료!");

  }

}
