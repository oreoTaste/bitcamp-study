package com.eomcs.util;

public interface List<E> {

  void add(E e);

  void add(int index, E e);

  E get(int index);

  E set(int index, E e);

  E remove(int index);

  E[] toArray();

  E[] toArray(E[] arr);

  int size();

}
