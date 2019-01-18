package com.eomcs.util;

// 22
import java.util.Arrays;

public class ArrayList<E> extends AbstractList<E> {
  private static final int DEFAULT_CAPACITY = 2;
  Object[] elementData;
  int size;

  public ArrayList() {
    this.elementData = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(final int initialCapacity) {
    if (initialCapacity < DEFAULT_CAPACITY) {
      this.elementData = new Object[DEFAULT_CAPACITY];
    } else {
      this.elementData = new Object[initialCapacity];
    }
  }

  @Override
  public void add(final E e) {
    if (this.elementData.length == this.size()) {
      grow();
    }
    this.elementData[this.size++] = e;
  }

  @Override
  public void add(final int index, final E e) {
    if (index < 0 || index > this.size) {
      return;
    }

    if (this.elementData.length == this.size()) {
      grow();
    }

    for (int i = size - 1; i > index; i--) {
      this.elementData[i] = this.elementData[i - 1];
    }

    this.elementData[index] = e;
    this.size++;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E get(final int index) {
    if (index < 0 || index >= this.size()) {
      System.out.println("유효하지 않은 값입니다");
      return null;
    }
    return (E) this.elementData[index];
  }

  private void grow() {
    this.elementData = Arrays.copyOf(this.elementData, newCapacity());
  }

  private int newCapacity() {
    final int oldCapacity = this.elementData.length;
    return oldCapacity + (oldCapacity >> 1);
  }

  @Override
  @SuppressWarnings("unchecked")
  public E remove(final int index) {
    if (index < 0 || index >= this.size()) {
      System.out.println("유효하지 않은 값입니다");
      return null;
    }
    final E oldValue = (E) this.elementData[index];
    System.arraycopy(this.elementData, index + 1, this.elementData, index, this.size() - index - 1);
    /*
     * for(int i = index ; i < this.size-1 ; i++) { this.elementData[i] = this.elementData[i+1]; }
     */
    this.elementData[--size] = null;
    return oldValue;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E set(final int index, final E e) {
    if (index < 0 || index >= this.size()) {
      System.out.println("유효하지 않은 값입니다");
      return null;
    }
    final E oldValue = (E) this.elementData[index];
    this.elementData[index] = e;
    return oldValue;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E[] toArray() {
    return (E[]) Arrays.copyOf(this.elementData, this.size());
  }

  @Override
  public E[] toArray(final E[] arr) {
    // if(arr.length < this.size) {
    // return (E[])Arrays.copyOf(this.elementData, this.size(), arr.getClass());
    // }
    System.arraycopy(this.elementData, 0, arr, 0, this.size);
    return arr;
  }
}


