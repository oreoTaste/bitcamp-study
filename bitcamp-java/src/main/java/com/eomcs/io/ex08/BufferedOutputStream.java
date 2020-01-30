//버퍼 기능을 입출력 플러그인/장신구(decorator)로 전환한다.
package com.eomcs.io.ex08;

import java.io.IOException;
import java.io.OutputStream;

public class BufferedOutputStream extends DecoratorOutputStream{

  byte[] buf = new byte[8196];
  int cursor;

  public BufferedOutputStream(OutputStream out) {
    super(out);
  }

  @Override
  public void write(int b) throws IOException {
    if (cursor == buf.length) { // 버퍼가 다차면
      부품.write(buf); // 버퍼에 들어있는 데이터를 한 번에 출력한다.
      cursor = 0; // 다시 커서를 초기화시킨다.
    }

    // 1바이트 출력하라고 하면 일단 버퍼에 저장할 것이다.
    buf[cursor++] = (byte)b;
  }

  @Override
  public void close() throws IOException {
    this.flush();
    부품.close();
  }

  @Override
  public void flush() throws IOException {
    if (cursor > 0) {
      부품.write(buf, 0, cursor);
      cursor = 0;
    }
  }

}







