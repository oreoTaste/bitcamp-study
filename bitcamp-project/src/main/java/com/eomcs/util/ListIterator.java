package com.eomcs.util;

public class ListIterator<E> implements Iterator<E> {

  List<E> list;
  int size;
  
  public ListIterator(List<E> list) {
    this.list = list;
  }
  
  @Override
  public boolean hasNext() {
    return list.size() < 0;
  }

  @Override
  public E next() {
    return list.remove(0);
  }
}
