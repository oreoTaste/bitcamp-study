// FileInputSteam 활용 - JPEG파일 읽기
package com.eomcs.io.ex02;

import java.io.File;
import java.io.FileInputStream;

public class Exam0410 {

  public static void main(String[] args) throws Exception {
    
    // 1) 파일의 데이터를 읽을 정보를 준비한다.
    File file = new File("sample/photo1.jpg");
    System.out.println(file);
    FileInputStream in = new FileInputStream(file);

    // SOI (Start of Image) 2byte 읽기
    int s1 = in.read();
    byte[] a = new byte[1];
    in.read(a);
    //int s2 = in.read();
    int soi = s1 << 8 | (a[0] & 0xff);
    System.out.printf("SOI : %x\n", soi);
    
    int jfifApp0Marker = in.read() << 8 | in.read();
    System.out.printf("JFIF marker : %x\n", jfifApp0Marker);
    
    int jfifApp0Length = in.read() << 8 | in.read();
    System.out.printf("length : %x\n", jfifApp0Length);

    byte[] jfifApp0Info = new byte[jfifApp0Length];
    in.read(jfifApp0Info);
    
    String jfifApp0Id = new String(jfifApp0Info, 0, 4);
    System.out.println(jfifApp0Id);
    
    int b;
    while (true) {
      b = in.read();
      if(b == -1) {
        break;
      }
      
      if(b == 0xff) {
        b = in.read();
        if(b == -1) {
          break;
        }
        if(b >= 0xc0 && b < 0xc2) {
          break;
        }
      }
    }
    
    if(b == -1) {
      System.out.println("유효한 JPEG파일이 아닙니다");
    }
    
    int sofLength = in.read() << 8 | in.read();
    System.out.printf("SOF 데이터크기 : %d\n", sofLength);
    
    byte[] sofData = new byte[sofLength];
    in.read(sofData);
    System.out.printf("SOF 샘플링 정밀도: %d\n", sofData[0]);
    
    int height = ((sofData[1] << 8) & 0xff00)  | (sofData[2] & 0xff);
    int width = ((sofData[3] << 8) & 0xff00) | (sofData[4] & 0xff);
    System.out.printf("SOF 이미지 크기 : %d, %d\n", width, height);
    
    // 2) 1바이트를 읽는다.
    // => read() 메서드의 리턴 타입이 int 라 하더라도 1바이트를 읽어 리턴한다.

    // 3) 읽기 도구를 닫는다.
    in.close();


  }

}
