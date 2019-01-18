package com.eomcs.util;

public abstract class AbstractList<E> implements List<E> {

  public abstract void add(E value);

  public abstract void add(int index, E value);

  public abstract E get(int index);

  public abstract E remove(int index);

  public abstract E set(int index, E value);

  public abstract E[] toArray();

  public abstract E[] toArray(E[] e);
}
