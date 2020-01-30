// 부품 연결 예
// FileOutputStrea -> BufferedOutputStream -> DataOutputStream
package com.eomcs.io.ex08;

import java.io.FileOutputStream;

public class Exam0310 {

  public static void main(String[] args) throws Exception {
    // 첫번째 부품
    FileOutputStream fileOut = new FileOutputStream("temp/test8.data");
    BufferedOutputStream bufOut = new BufferedOutputStream(fileOut);
    // 부품추가
    // - 기존의 FileOutputStream 객체에 String,int,boolean 값을 출력하는
    // 장신구/보조장치/플러그인(decorator)을 장착한다.
    DataOutputStream out = new DataOutputStream(bufOut);

    Member member = new Member();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;
    
    long start = System.currentTimeMillis();
    for(int i = 0; i < 100000; i++) {
      out.writeUTF(member.name);
      out.writeInt(member.age);
      out.writeBoolean(member.gender);
    }
    out.close();
    long end = System.currentTimeMillis();

    System.out.println("데이터 출력 완료!");
    System.out.println(end - start);
    // 문제?
    // => DataBufferedOutputStream과 DataOutputStream 클래스는
    //    생성자를 빼고 나머지 코드가 모두 같다.
    // => 안타깝게도 DataInputStream의 코드를 복사하지 않고
    //    재사용하는 방법이 없다.
    //    이것이 상속으로 기능을 확장했을 때의 한계이다.
    // => 해결책?
    //    바이트 값을 읽어 String,int,boolean 값으로 바꾸는 코드를
    //    장신구(decorator)처럼 붙였다 뗐다 하게 만들라!

  }

}
