// DataOutputStream을 이용하여 객체 출력 - 인스턴스의 값을 출력
package com.eomcs.io.ex08;

import java.io.FileOutputStream;

public class Exam0110 {

  public static void main(String[] args) throws Exception {
    // 상속을 통한 기능확장의 문제점
    // - 자바는 다중상속이 불가능하다.
    //   따라서, 여러 기능을 조합하려면 기존 코드를 복사해야한다.
    // =>코드중복
    
    FileOutputStream fileOut = new FileOutputStream("temp/test6.data");

    // 부품추가
    // - 기존의 FileOutputStream 객체에 String,int,boolean 값을 출력하는
    // 장신구/보조장치/플러그인(decorator)을 장착한다.
    DataOutputStream out = new DataOutputStream(fileOut);

    Member member = new Member();
    member.name = "AB가각간";
    member.age = 27;
    member.gender = true;

    // 장신구/보조장치/플러그인(decorator)를 사용하여 String,int,boolean 값을 출력한다.
    out.writeUTF(member.name);
    out.writeInt(member.age);
    out.writeBoolean(member.gender);

    out.close();

    System.out.println("데이터 출력 완료!");
    
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
