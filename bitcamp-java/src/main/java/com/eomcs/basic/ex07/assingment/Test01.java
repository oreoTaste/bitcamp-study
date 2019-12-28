package com.eomcs.basic.ex07.assingment;

public class Test01 {
  public static void main(String[] args) {
    //배열의 값 중에서 최소, 최대값을 출력하라.
    
    
    int[] values = {34,4,-3,78,12,22,45,0,-22};
    int value = max(values);
    System.out.println(value);
    
    
    
    
    
    
  }
  
  static int min(int[] values) {
    // 파라미터로 배열을 받아서 그 값 중에서 최대값을 찾아 리턴한다
    int min = values[0];
    for(int i=1 ; i<values.length ; i++) {
      if(min > values[i]) min = values[i];
    }
    return min;
  }
  
  static int max(int[] values) {
    // 파라미터로 배열을 받아서 그 값 중에서 최대값을 찾아 리턴한다
    int max = values[0];
    for(int i=1 ; i<values.length ; i++) {
      if(max < values[i]) max = values[i];
    }
    return max;
  }
}
