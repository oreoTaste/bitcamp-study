package com.eomcs.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {

  @Override
  public Queue<E> clone() {
    // deep copy 시전!
    final Queue<E> temp = new Queue<>();

    for (int i = 0; i < size(); i++) {
      temp.offer(get(i));
    }
    return temp;
  }

  @Override
  public Iterator<E> iterator() {

    final Iterator<E> obj = new Iterator<>() {
      Queue<E> queue;

      {
        this.queue = Queue.this.clone();
      }

      @Override
      public boolean hasNext() {
        return queue.size > 0;
      }

      @Override
      public E next() {
        return queue.poll();
      }
    };

    return obj;
  }

  /*
   * @Override public Queue clone() { try { return (Queue) super.clone(); // shallow- copy. } catch
   * (CloneNotSupportedException e) { System.out.println(e); return null; } }
   */

  public void offer(final E object) {
    this.add(object);
  }

  public E poll() {
    return remove(0);
  }

}
