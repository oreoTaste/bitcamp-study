package com.eomcs.util;

public interface List<E> {
  int size = 0;

  public abstract void add(E value);

  public abstract void add(int index, E value);

  public abstract E get(int index);

  public abstract E remove(int index);

  public abstract E set(int index, E value);

  int size();

  public abstract E[] toArray();

  public abstract E[] toArray(E[] e);

}
