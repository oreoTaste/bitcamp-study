package com.eomcs.io.ex06;

import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStream extends FileInputStream {
  byte[] buf = new byte[8192];
  int size; // 배열에 저장되어 있는 바이트의 수
  int cursor; // 바이트 읽은 배열의 위치

  public BufferedInputStream(String filename) throws Exception {
    super(filename);
  }

  @Override
  public int read() throws IOException {
    if (cursor == size) {    // 버퍼에 저장되어 있는 데이터를 모두 읽었다는 의미
      if ((size = super.read(buf)) == -1)
        return -1;
      cursor = 0;
    }
    return buf[cursor++] & 0x000000ff;
  }
  
  @Override
  public int read(byte[] buf) throws IOException {
    int i = 0;
    for(; i< buf.length; i++) {
      int b = this.read();
      if(b == -1) {
        break;
      }
      buf[i] = (byte) b;
    }
    return i;
  }
}


