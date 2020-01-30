package com.eomcs.io.ex08;

import java.io.IOException;
import java.io.InputStream;

public abstract class DecoratorInputStream extends InputStream{
  InputStream 부품;
  
  public DecoratorInputStream(InputStream in) {
    
    this.부품 = in;
  }
  
  @Override
  public int read() throws IOException {
    // read()가 호출되면 이 장식품과 연결된 다른 부품의 read를 실행한다.
    return 부품.read();
  }
  
  @Override
  public void close() throws IOException {
    부품.close();
  }
  
}
