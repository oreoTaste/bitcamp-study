package com.eomcs.util;

public class Queue<E> extends LinkedList<E> implements Cloneable {

  public void offer(E object) {
    this.add(object);
  }

  public E poll() {
    return remove(0);
  }

  /*
   * @Override public Queue clone() { try { return (Queue) super.clone(); // shallow- copy. } catch
   * (CloneNotSupportedException e) { System.out.println(e); return null; } }
   */

  @Override
  public Queue<E> clone() {
    // deep copy 시전!
    Queue<E> temp = new Queue<>();

    for (int i = 0; i < size(); i++) {
      temp.offer(get(i));
    }
    return temp;
  }

  @Override
  public Iterator iterator() {
    return new Iterator() {
      Queue queue;

      {
        queue = Queue.this.clone();
      }

      @Override
      public boolean hasNext() {
        return queue.size > 0;
      }

      @Override
      public Object next() {
        return queue.poll();
      }

    };
  }


}
