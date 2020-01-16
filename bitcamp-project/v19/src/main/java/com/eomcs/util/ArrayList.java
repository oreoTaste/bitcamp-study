package com.eomcs.util;

import java.util.Arrays;

public class ArrayList<E> {

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

  @SuppressWarnings("unchecked")
  public E[] toArray() {
    return (E[]) Arrays.copyOf(this.list, this.size);
  }

  @SuppressWarnings({"unchecked"})
  public E[] toArray(E[] arr) {
    if(arr.length < this.size) {
      return (E[])Arrays.copyOf(this.list, this.size, arr.getClass());
    }
    System.arraycopy(this.list, 0, arr, 0, this.size);
    /*
    for(int i = 0 ; i < arr.length ; i++) {
      arr[i] = (E)this.list[i];
    } 
    */
    return arr;
  }
  
  //////////////////////////////////////////////////////////////

  public void add(E board) {
    if(this.list.length == this.size) {
      grow();
    }
    this.list[this.size++] = board;
  }

  private void grow() {
    this.list = Arrays.copyOf(this.list, newCapacity());
  }

  private int newCapacity() {
    int oldCapacity = this.list.length;
    return oldCapacity + (oldCapacity >> 1);
  }

  public void add(int index, E board) {
    if(index < 0 || index > this.size) {
      return;
    }
    
    if(this.list.length == this.size) {
      grow();
    }
    
    
    for(int i = size-1 ; i > index ; i--) {
      this.list[i] = this.list[i-1];
    }
    this.list[index] = board;
    this.size++;
  }
  
  //////////////////////////////////////////////////////////////

  @SuppressWarnings("unchecked")
  public E get(int idx) {
    if(0 <= idx && idx < size)
      return (E)this.list[idx];
    else return null;
  }

  
  @SuppressWarnings("unchecked")
  public E set(int index, E obj) {
    if(index < 0 || index > this.size) return null;
    
    E old = (E)this.list[index];
    this.list[index] = obj;
    return old;
  }

  
  @SuppressWarnings("unchecked")
  public E remove(int index, E obj) {
    if(index < 0 || index > this.size) return null;
    E old = (E) this.list[index];
    this.list[index] = null;

    for(int i = index ; i < this.size ; i ++){
      this.list[index] = this.list[index+1];
    }
    this.list[size-1] = null;
    this.size--;
    return old;
  }
  

  public int size() {
    return this.size;
  }

}
