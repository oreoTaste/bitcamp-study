package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> {
  private static final int DEFAULT_CAPACITY = 2;
  Object[] elementData;
  private int size;

  public ArrayList() {
    this.elementData = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int initialCapacity) {
    if(initialCapacity < DEFAULT_CAPACITY) {
      this.elementData = new Object[DEFAULT_CAPACITY];
    } this.elementData = new Object[initialCapacity];
  }

  public int size() {
    return this.size;
  }

  public void add(E e) {
    int oldCapacity = this.elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);

    if(oldCapacity == this.size()) {
      //System.arraycopy(this.elementData, 0, newElement, 0, newCapacity);
      this.elementData = Arrays.copyOf(this.elementData, newCapacity);
    }
    this.elementData[this.size++] = e;
  }

  
  @SuppressWarnings("unchecked")
  public E get(int index) {
    if(index < 0 || index >= this.size()) {
      System.out.println("유효하지 않은 값입니다");
      return null;
    }
    return (E)this.elementData[index];
  }

  
  @SuppressWarnings("unchecked")
  public E set(int index, E e) {
    if(index < 0 || index >= this.size()) {
      System.out.println("유효하지 않은 값입니다");
      return null;
    }
    E oldValue = (E)this.elementData[index];
    this.elementData[index] = e;
    return oldValue;
  }

  
  @SuppressWarnings("unchecked")
  public E remove(int index) {
    if(index < 0 || index >= this.size()) {
      System.out.println("유효하지 않은 값입니다");
      return null;
    }
    E oldValue = (E)this.elementData[index];
    System.arraycopy(this.elementData, index+1, this.elementData, index, this.size() - index - 1);
    /*
    for(int i = index ; i < this.size-1 ; i++) {
      this.elementData[i] = this.elementData[i+1];
    }*/
    this.elementData[--size] = null;
    return oldValue;
  }
  
  @SuppressWarnings("unchecked")
  public E[] toArray() {
    return (E[])Arrays.copyOf(this.elementData, this.size());
  }

  public E[] toArray(E[] arr) {
    //if(arr.length < this.size) {
    //  return (E[])Arrays.copyOf(this.elementData, this.size(), arr.getClass());
    //}
    System.arraycopy(this.elementData, 0, arr, 0, this.size);
    return arr;
  }
}








