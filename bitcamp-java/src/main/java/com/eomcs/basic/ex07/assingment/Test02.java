package com.eomcs.basic.ex07.assingment;

public class Test02 {
  public static void main(String[] args) throws Exception {
    int[] values = {34,4,-3,78,12,22,45,0,-22};
    sort(values); // 오름차순으로 정렬을 수행
    
  }
  
  static void sort(int[] values) throws Exception {
    for(int i=0 ; i<values.length ; i++) {
      for(int j=0 ; j<i ; j++) {
        Thread.currentThread().sleep(1000);
        printValues(values, i, j);
        if(values[i] < values[j]) {
          int temp = values[i];
          values[i] = values[j];
          values[j] = temp;
          i--;
        }
      }
    }
  }
  
  static void printValues(int[] values, int currPos1, int currPos2) {
    for(int i=0 ; i<values.length ; i++) {
      if(i == currPos1 || i == currPos2) {
        System.out.printf("[%5d] ", values[i]);
      } else System.out.printf("%5d ", values[i]);
    } System.out.println();
  }
  
}
















