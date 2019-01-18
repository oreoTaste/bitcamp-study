package com.eomcs;

import java.util.Iterator;

class MyData {
  int[] arr;

  public MyData(final int[] arr) {
    this.arr = arr;
  }

  public int getElement(final int index) {
    return arr[index];
  }

  public Iterator iterator() {
    return new Iterator<>() {
      MyData md;
      int cursor;

      {
        md = MyData.this;
        cursor = 0;
      }

      @Override
      public boolean hasNext() {
        return cursor < arr.length;
      }

      @Override
      public Object next() {
        return md.getElement(cursor++);
      }

    };
  }
}


public class Test200 {
  public static void main(final String[] args) {
    final int[] arr = {1, 2, 3, 4, 5};

    final MyData data = new MyData(arr);
    final Iterator<Integer> it = data.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}
