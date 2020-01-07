package com.eomcs.lms.handler;

import java.util.ArrayList;
import java.util.Arrays;
import com.eomcs.lms.domain.Lesson;

public class LessonList {
  int size = 0;
  Lesson[] list;
  static final int DEFAULT_CAPACITY = 100;

  public LessonList() {
    list = new Lesson[DEFAULT_CAPACITY];
  }

  public LessonList(int capacity) {
    if(capacity < DEFAULT_CAPACITY || capacity > 100_000 )
      list = new Lesson[DEFAULT_CAPACITY];
    else
      list = new Lesson[capacity];
  }

  /////////////////////////////////////////////////////////////////////////////////
  
  public void add(Lesson les) {
    int oldCapacity = this.list.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if(this.list.length == this.size) {
      this.list = Arrays.copyOf(this.list, newCapacity);;
    }
    this.list[this.size++] = les;
  }
  
  /////////////////////////////////////////////////////////////////////////////////

  public Lesson[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }  
}
