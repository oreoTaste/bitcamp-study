package com.eomcs.util;

import java.util.Arrays;

public class Stack<E> implements Cloneable {

  private Object[] elementData;
  private final int DEFAULT_CAPACITY;
  int size;

  public Stack() {
    DEFAULT_CAPACITY = 10;
    this.elementData = new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  // deep-clone방식
  @Override
  @SuppressWarnings("unchecked")
  public Stack<E> clone() {
    try {
      final Stack<E> temp = (Stack<E>) super.clone();

      final Object[] arr = new Object[this.size];
      for (int i = 0; i < this.size; i++) {
        arr[i] = this.elementData[i];
      }

      temp.elementData = arr;

      return temp;
    } catch (final CloneNotSupportedException ex) {
      System.out.println(ex);
      return null;
    }

  }


  public boolean empty() {
    return this.size == 0;
  }


  private void grow() {
    elementData = Arrays.copyOf(elementData, newCapacity());
  }


  public Iterator<E> iterator() {

    return new Iterator<>() {
      Stack<E> stack;

      {
        this.stack = Stack.this.clone();
      }

      @Override
      public boolean hasNext() {
        return !stack.empty();
      }

      @Override
      public E next() {
        return stack.pop();
      }
    };
  }


  private int newCapacity() {
    final int oldCapacity = elementData.length;
    return oldCapacity + (oldCapacity >> 1);
  }

  @SuppressWarnings("unchecked")
  public E peek() {
    return (E) this.elementData[size - 1];
  }

  /*
   * @Override public Stack clone() { try { return (Stack) super.clone(); }
   * catch(CloneNotSupportedException ex) { System.out.println(ex); return null; } }
   */

  @SuppressWarnings("unchecked")
  public E pop() {
    if (empty()) {
      return null;
    }
    final E popValue = (E) this.elementData[--size];
    this.elementData[size] = null;
    return popValue;
  }


  public void push(final E value) {
    if (this.size == elementData.length) {
      grow();
    }

    this.elementData[size] = value;
    size++;
  }


}
