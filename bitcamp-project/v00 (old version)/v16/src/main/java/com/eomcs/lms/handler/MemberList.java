package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Member;

public class MemberList {
  Member[] list;
  int size = 0;
  static final int DEFAULT_CAPACITY = 100;

  public MemberList() {
    this.list = new Member[DEFAULT_CAPACITY];
  }

  public MemberList(int capacity) {
    if(capacity < DEFAULT_CAPACITY || capacity > 100_000)
      this.list = new Member[DEFAULT_CAPACITY];
    else 
      this.list = new Member[capacity];
  }

  /////////////////////////////////////////////////////////////////////////////////////

  public void add(Member mem) {
    if(this.list.length == size) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = mem;
  }

  /////////////////////////////////////////////////////////////////////////////////////

  public Member[] toArray() {
    return Arrays.copyOf(this.list, size);
  }
}
