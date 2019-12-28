package com.eomcs.basic.ex07.assingment;

public class Test03 {
  static int[] values = {34,4,-3,78,12,22,45,0,-22};
  public static void main(String[] args) {
    // 배열이 들어있는 값을 역순으로 변경하라
    // => -22,0,45,22,12....
    System.out.printf("메인// values : %s \n", values);
    reverseNo(values);
    printValues(values);
  }

  
  static void reverseNo(int[] val) {
    System.out.printf("리버스//   val : %s \n", val);
    
    int[] newVal = new int[val.length];
    System.out.printf("리버스//newVal : %s \n", newVal);
    for(int i=0 ; i<val.length ; i++) {
      newVal[i] = val[val.length-1-i];
    } val = newVal;
    System.out.printf("리버스//   val : %s \n", val);
  }
   

  static void reverse(int[] values) {
    int[] newVal = values.clone();
    for(int i=0 ; i<values.length ; i++) {
      values[i] = newVal[values.length-1-i];
    } 
  }

  static void reverse2(int[] values) {
    for(int i = 0 ; i < (values.length >> 1) ; i++) {
      int temp = values[i];
      values[i] = values[values.length-i-1];
      values[values.length-i-1] = temp;
    }
  }
  
  
  
  
  
  
  
  static void printValues(int[] values) {
    for(int i=0 ; i<values.length ; i++) {
      System.out.printf("%3d ", values[i]);
    } System.out.println();
  }

  static void printValues(int[] values, int currPos1, int currPos2) {
    for(int i=0 ; i<values.length ; i++) {
      if(i == currPos1 || i == currPos2) {
        System.out.printf("[%5d] ", values[i]);
      } else System.out.printf("%5d ", values[i]);
    } System.out.println();
  }

}