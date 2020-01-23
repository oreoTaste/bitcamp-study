package com.eomcs.util;

import java.util.Arrays;

public class Stack implements Cloneable{
  private Object[] elementData;
  private final int DEFAULT_CAPACITY;
  int size;

  public Stack() {
    DEFAULT_CAPACITY = 10;
    this.elementData = new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  public Object pop() {
    if(empty())
      return null;
    Object popValue = this.elementData[--size];
    this.elementData[size] = null;
    return popValue;
  }


  public void push(Object value) {
    if(this.size == elementData.length) {
      grow();
    }

    this.elementData[size] = value;
    size++;
  }
  
  
  public boolean empty() {
    return this.size == 0;
  }


  public Object peek() {
    return this.elementData[size-1];
  }


  private void grow() {
    elementData = Arrays.copyOf(elementData, newCapacity());
  }

  private int newCapacity() {
    int oldCapacity = elementData.length;
    return oldCapacity + (oldCapacity>>1);
  }

  /*
  @Override
  public Stack clone() {
    try {
    return (Stack) super.clone();
    } catch(CloneNotSupportedException ex) {
      System.out.println(ex);
      return null;
    }
  }
   */

  
  // deep-clone방식
  public Stack clone() {
    try {
      Stack temp = (Stack) super.clone();

      Object[] arr = new Object[this.size];
      for(int i = 0 ; i < this.size ; i++) {
        arr[i] = this.elementData[i];
      }

      temp.elementData = arr;

      return temp;
    } catch(CloneNotSupportedException ex) {
      System.out.println(ex);
      return null;
    }

  }
}
