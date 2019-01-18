package com.eomcs.util;

public class Queue<E> extends LinkedList<Object> implements Cloneable {

  @Override
  @SuppressWarnings("unchecked")
  public Queue<E> clone() {
    // deep copy 시전!
    final Queue<E> temp = new Queue<>();

    for (int i = 0; i < this.size(); i++) {
      temp.offer((E) get(i));
    }
    return temp;
  }

  public void offer(final E object) {
    this.add(object);
  }

  public E poll() {
    return (E) remove(0);
  }

  /*
   * @Override public Queue clone() { try { return (Queue) super.clone(); // shallow- copy. } catch
   * (CloneNotSupportedException e) { System.out.println(e); return null; } }
   */

  @Override
  public int size() {
    return this.size();
  }


}
