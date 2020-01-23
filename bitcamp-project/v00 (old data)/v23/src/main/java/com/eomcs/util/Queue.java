package com.eomcs.util;

public class Queue <E> extends LinkedList<E> implements Cloneable{

  public void offer(E object) {
    this.add(object);
  }

  public E poll() {
    return this.remove(0);
  }

  /*
 @Override
  public Queue clone() {
   try {
    return (Queue) super.clone(); // shallow- copy.
   } catch (CloneNotSupportedException e) {
     System.out.println(e);
     return null;
   }
  }
   */

  public Queue<E> clone() {
    //deep copy 시전!
    Queue<E> temp = new Queue<E>();
    
    for (int i = 0; i < this.size(); i++) {
      temp.offer(this.get(i));
    }
    return temp;
  }


}
