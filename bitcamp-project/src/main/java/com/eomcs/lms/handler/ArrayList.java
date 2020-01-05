package com.eomcs.lms.handler;

import java.util.Arrays;

public class ArrayList {

  static final int DEFAULT_CAPACITY = 100;
  int size = 0;
  Object[] list;

  public ArrayList() {
    this.list = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList (int capacity) {
    if(capacity < DEFAULT_CAPACITY || capacity > 100_000)
      this.list = new Object[DEFAULT_CAPACITY];
    else
      this.list = new Object[capacity];
  }

  //////////////////////////////////////////////////////////////

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }

  //////////////////////////////////////////////////////////////

  public void add(Object board) {
    if(this.list.length == this.size) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = board;
  }

  //////////////////////////////////////////////////////////////

  public Object get(int idx) {
    if(0 <= idx && idx < size)
      return this.list[idx];
    else return null;
  }
}
