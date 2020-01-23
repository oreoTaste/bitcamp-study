package com.eomcs.util;

import java.lang.reflect.Array;

public class LinkedList<E> {
  Node<E> first;
  Node<E> last;
  int size;

  @SuppressWarnings("unchecked")
  public void add(E value) {
    Node<E> newNode = new Node<>();
    newNode.value = value;

    if(first == null) {
      last = first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }
    size++;
  }

  // 오버로딩
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
      size++;
    }
    else {
      newNode.next = cursor.next;
      cursor.next = newNode;
      size++;
    }
  }

  public E remove(int index) {
    if(index < 0 || index >= size) {
      return null;
    }
    Node<E> cursor = first;
    for(int i = 0 ; i < index-1 ; i++) {
      cursor = cursor.next;
    } 

    Node<E> removal;
    if(index == 0) {
      removal = first;
      first = first.next;;
    }

    else {
      removal = cursor.next;
      cursor.next = cursor.next.next;
    }
    removal.next = null;
    size--;
    return removal.value;
  }


  @SuppressWarnings("unchecked")
  public E set(int index, Object value) {
    if(index < 0 || index >= size) {
      return null;
    }
    Node<E> cursor = first;
    for(int i = 0 ; i < index ; i++) {
      cursor = cursor.next;
    }
    E oldValue = cursor.value;
    cursor.value = (E) value;
    return oldValue;
  }


  public Object[] toArray() {
    Object[] arr = new Object[size];

    Node<E> cursor = first;
    
    for(int i = 0 ; i < size ; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    } return arr;
  }

  @SuppressWarnings("unchecked")
  public E[] toArray(E[] arr) {
    if(arr.length < size) {
      arr = (E[]) Array.newInstance(arr.getClass().getComponentType(), size);
    }

    Node<E> cursor = first;
    
    for(int i = 0 ; i < size ; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    } return arr;
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

  public int size() {
    return this.size;
  }

  
  
  static class Node<T> {
    T value;
    Node<T> next;


  }
  
  
}