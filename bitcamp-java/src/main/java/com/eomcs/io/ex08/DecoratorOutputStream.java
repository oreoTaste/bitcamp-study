package com.eomcs.io.ex08;

import java.io.IOException;
import java.io.OutputStream;

public abstract class DecoratorOutputStream extends OutputStream{
  OutputStream 부품;
  
  public DecoratorOutputStream(OutputStream out) {
    this.부품 = out;
  }
  
  @Override
  public void write(int b) throws IOException {
    부품.write(b);
  }
  
  @Override
  public void close() throws IOException {
    부품.close();
  }
  
}
