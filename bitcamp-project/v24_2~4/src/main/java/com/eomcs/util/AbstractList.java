package com.eomcs.util;

public abstract class AbstractList<E> implements List<E> {

  protected int size;

  @Override
  public Iterator<E> iterator() {
    class ListIterator<E> implements Iterator<E> {
      List<E> list;
      int cursor;

      public ListIterator(final List<E> list) {
        this.list = list;
      }

      @Override
      public boolean hasNext() {
        return cursor < list.size();
      }

      @Override
      public E next() {
        return list.get(cursor++);
      }
    }
    return new ListIterator<>(this);
  }

  @Override
  public int size() {
    return this.size;
  }

}


