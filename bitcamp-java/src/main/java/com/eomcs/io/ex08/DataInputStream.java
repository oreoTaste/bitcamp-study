// DataInputStream을 FileInputStream이나 ByteArrayInputStream에 붙일 수 있도록
// 기능을 변경한다. 즉 DataInputStream을 장신구(decorator)로 만든다.
package com.eomcs.io.ex08;

import java.io.IOException;
import java.io.InputStream;

public class DataInputStream extends DecoratorInputStream {

  public DataInputStream(InputStream in) {
    super(in); // 다른 장식품과 연결하기 위해 수퍼클래스에 생성자를 호출
  }

  public String readUTF() throws Exception {
    // 생성자에서 받은 입력 객체의 read() 메서드를 사용하여 문자열 출력
    int size = 부품.read();
    byte[] bytes = new byte[size];
    부품.read(bytes);
    return new String(bytes, "UTF-8");
  }

  public int readInt() throws Exception {
    // 생성자에서 받은 입력 객체의 read() 메서드를 사용하여 int 값 출력
    int value = 0;

    value = 부품.read() << 24;
    value += 부품.read() << 16;
    value += 부품.read() << 8;
    value += 부품.read();
    return value;
  }

  public long readLong() throws Exception {
    // 생성자에서 받은 입력 객체의 read() 메서드를 사용하여 long 값 출력
    long value = 0;
    value += (long)부품.read() << 56;
    value += (long)부품.read() << 48;
    value += (long)부품.read() << 40;
    value += (long)부품.read() << 32;
    value += (long)부품.read() << 24;
    value += (long)부품.read() << 16;
    value += (long)부품.read() << 8;
    value += 부품.read();
    return value;
  }

  public boolean readBoolean() throws Exception {
    // 생성자에서 받은 입력 객체의 read() 메서드를 사용하여 boolean 값 출력
    if (부품.read()==1)
      return true;
    else
      return false;
  }

  @Override
  public int read() throws IOException {
    // TODO Auto-generated method stub
    return 0;
  }
}





