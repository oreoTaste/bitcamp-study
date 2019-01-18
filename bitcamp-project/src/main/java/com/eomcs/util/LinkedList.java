package com.eomcs.util;

import java.lang.reflect.Array;

public class LinkedList<E> extends AbstractList<E> {
  static class Node<T> {
    T value;
    Node<T> next;
  }

  Node<E> first;
  Node<E> last;

  int size;

  @Override
  public void add(final E value) {
    final Node<E> newNode = new Node<>();
    newNode.value = value;

    if (first == null) {
      last = first = newNode;
    } else {
      last.next = newNode;
      last = newNode;
    }
    size++;
  }

  @Override
  public void add(final int index, final E value) {
    if (index < 0 || index >= size) {
      return;
    }
    final Node<E> newNode = new Node<>();
    newNode.value = value;

    Node<E> cursor = first;
    for (int i = 0; i < index - 1; i++) {
      cursor = cursor.next;
    }

    if (index == 0) {
      newNode.next = first;
      first = newNode;
    } else {
      newNode.next = cursor.next;
      cursor.next = newNode;
    }
    size++;
  }

  @Override
  public E get(final int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    Node<E> cursor = first;
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    return cursor.value;
  }

  @Override
  public E remove(final int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    Node<E> cursor = first;
    for (int i = 0; i < index - 1; i++) {
      cursor = cursor.next;
    }
    Node<E> deletedValue;
    if (index == 0) {
      deletedValue = first;
      first = first.next;
    } else {
      deletedValue = cursor.next;
      cursor.next = deletedValue.next;
    }
    deletedValue.next = null;
    size--;
    return deletedValue.value;
  }

  @Override
  public E set(final int index, final E value) {
    if (index < 0 || index >= size) {
      return null;
    }
    Node<E> cursor = first;
    for (int i = 0; i < index; i++) {
      cursor = cursor.next;
    }
    final E oldValue = cursor.value;
    cursor.value = value;
    return oldValue;
  }

  @Override
  public int size() {
    return this.size;
  }



  @Override
  @SuppressWarnings("unchecked")
  public E[] toArray() {
    final E[] arr = (E[]) new Object[size];
    Node<E> cursor = first;
    for (int i = 0; i < this.size; i++) {
      arr[i] = cursor.value;
      cursor = cursor.next;
    }
    return arr;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E[] toArray(E[] e) {
    if (e.length < size) {
      e = (E[]) Array.newInstance(e.getClass().getComponentType(), size);
    }
    Node<E> cursor = first;

    for (int i = 0; i < this.size; i++) {
      e[i] = cursor.value;
      cursor = cursor.next;
    }
    return e;
  }
}

