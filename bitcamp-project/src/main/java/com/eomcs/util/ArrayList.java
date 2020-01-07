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

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
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
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = board;
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

  public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<>();
    list.add("aaaa");
    list.add("bbbb");
    list.add("cccc");
    list.add("dddd");
    list.add("eeee");
    list.add("ffff");
    
    list.set(0, "changed");
    list.set(1, "changed");
    list.set(-1, "changed");
    list.set(6, "changed");
    
    String[] arr = list.toArray(new String[] {});
    for(String s : arr) {
      System.out.println(s);
    }
  }
  
  
}
