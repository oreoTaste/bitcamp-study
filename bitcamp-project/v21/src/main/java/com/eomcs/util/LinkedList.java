package com.eomcs.util;

import java.lang.reflect.Array;

public class LinkedList<E> {
  Node<E> first;
  Node<E> last;
  private int size;
  
  public void add(E value) {
    Node<E> newNode = new Node<>();
    newNode.value = value;
    
    if(first == null) {
      last = first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
      size++;
    }
  }
  
  public E get(int index) {
    if(index < 0 || index >= size) {
      return null;
    }
    Node<E> cursor = first;
    for(int i = 0 ; i < index ; i++) {
      cursor = cursor.next;
    } return cursor.value;
  }
  
  public void add(int index, E value) {
    if(index < 0 || index >= size) {
      return;
    }
    Node<E> newNode = new Node<>();
    newNode.value = value;

    Node<E> cursor = first;
    for(int i = 0 ; i < index-1 ; i++) {
      cursor = cursor.next;
    } 
    
    if(index == 0) {
      newNode.next = first;
      first = newNode;
    }
    else {
      newNode.next = cursor.next;
      cursor.next = newNode;
    }
    size++;
  }

  public E remove(int index) {
    if(index < 0 || index >= size) {
      return null;
    }
    Node<E> cursor = first;
    for(int i = 0 ; i < index-1 ; i++) {
      cursor = cursor.next;
    } 
    Node<E> deletedValue;
    if(index == 0) {
      deletedValue = first;
      first = first.next;
    }
    else {
      deletedValue = cursor.next;
      cursor.next = deletedValue.next;
    }
    deletedValue.next = null;
    size--;
    return deletedValue.value;
  }

  
  public E set(int index, E value) {
    if(index < 0 || index >= size) {
      return null;
    }
    Node<E> cursor = first;
    for(int i = 0 ; i < index ; i++) {
      cursor = cursor.next;
    } 
    E oldValue = cursor.value;
    cursor.value = value;
    return oldValue;
  }

  public Object[] toArray() {
    Object[] arr = new Object[size];
    Node<E> cursor = first;
    for(int i = 0 ; i < this.size ; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    } return arr;
  }
  
  public int size() {
    return this.size;
  }
  
  
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] e) {
    if(e.length < size) {
      e = (E[])Array.newInstance(e.getClass().getComponentType(), size);
    }
    Node<E> cursor = first;
    
    for(int i = 0 ; i < this.size ; i++) {
      e[i] = cursor.value;
      cursor = cursor.next;
    }
    return e;
  }
  
  
  static class Node<T> {
    T value;
    Node<T> next;
  }
}

