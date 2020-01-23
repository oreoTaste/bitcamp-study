package com.eomcs.util;

public abstract class AbstractList<E> implements List<E> {

  protected int size;

  @Override
  public Iterator<E> iterator() {

    final Iterator<E> obj = new Iterator<>() {
      List<E> list;
      int cursor;

      {
        this.list = AbstractList.this;
      }

      @Override
      public boolean hasNext() {
        return cursor < list.size();
      }

      @Override
      public E next() {
        return list.get(cursor++);
      }
    };

    return obj;
  }

  @Override
  public int size() {
    return this.size;
  }

}


