package com.eomcs.io.ex08;

import java.io.IOException;
import java.io.OutputStream;

public class DataOutputStream extends DecoratorOutputStream {

  public DataOutputStream(OutputStream out) {
    super(out);
  }

  public void writeUTF(String str) throws Exception {
    // 생성자에서 받은 출력 객체의 write() 메서드를 사용하여 문자열 출력
    byte[] bytes = str.getBytes("UTF-8");
    부품.write(bytes.length);
    부품.write(bytes);
  }

  public void writeInt(int value) throws Exception {
    // 생성자에서 받은 출력 객체의 write() 메서드를 사용하여 int 값 출력
    부품.write(value >> 24);
    부품.write(value >> 16);
    부품.write(value >> 8);
    부품.write(value);
  }

  public void writeLong(long value) throws Exception {
    // 생성자에서 받은 출력 객체의 write() 메서드를 사용하여 long 값 출력
    부품.write((int)(value >> 56));
    부품.write((int)(value >> 48));
    부품.write((int)(value >> 40));
    부품.write((int)(value >> 32));
    부품.write((int)(value >> 24));
    부품.write((int)(value >> 16));
    부품.write((int)(value >> 8));
    부품.write((int)value);
  }

  public void writeBoolean(boolean value) throws Exception {
    // 생성자에서 받은 출력 객체의 write() 메서드를 사용하여 boolean 값 출력
    if (value) {
      부품.write(1);
    } else {
      부품.write(0);
    }
  }

  @Override
  public void close() throws IOException {
    부품.close();
  }
}





