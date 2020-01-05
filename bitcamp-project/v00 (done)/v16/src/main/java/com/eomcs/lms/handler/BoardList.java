package com.eomcs.lms.handler;

import java.util.Arrays;
import com.eomcs.lms.domain.Board;

public class BoardList {

  static final int DEFAULT_CAPACITY = 100;
  int size = 0;
  Board[] list;

  public BoardList() {
    this.list = new Board[DEFAULT_CAPACITY];
  }

  public BoardList (int capacity) {
    if(capacity < DEFAULT_CAPACITY || capacity > 100_000)
      this.list = new Board[DEFAULT_CAPACITY];
    else
      this.list = new Board[capacity];
  }

  //////////////////////////////////////////////////////////////

  public Board[] toArray() {
    /*
    Board[] arr = new Board[this.size];
    for(int i = 0 ; i < this.size ; i++) {
      arr[i] = this.list[i];
    }
    */
    return Arrays.copyOf(this.list, this.size);
  }

  //////////////////////////////////////////////////////////////

  public void add(Board board) {
    if(this.list.length == this.size) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      
      /*
      Board[] arr= new Board[newCapacity];
      
      for(int i = 0 ; i < this.list.length ; i++) {
        arr[i] = this.list[i];
      }
      this.list = arr;
      */
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = board;
  }

  //////////////////////////////////////////////////////////////

  public Board get(int no) {
    for(int i = 0 ; i < this.size ; i++) {
      if(this.list[i].getNo() == no) {
        return this.list[i];
      }
    }
    return null;
  }
}
