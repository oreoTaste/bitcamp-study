// 비트 이동 연산자 : >>, >>>, << 
package com.eomcs.basic.ex05;

public class Exam04_3 {
    public static void main(String[] args) {
//     >>> 비트이동연산자 사용법
//     왼쪽 빈자리를 무조건 0으로 채운다.
      
      int i = 0b01101001; // 105
//    00000000 00000000 000000000 01101001 => 105
//    00000000 00000000 000000000 00110100 => 52
      System.out.println(i); 
      System.out.println(i >>> 1); // 00110100|1 => 52

//    00000000 00000000 000000000 01101001 => 105
//    00000000 00000000 000000000 00011010 => 26

      System.out.println(i >>> 2); // 00011010|0 => 26

//    00000000 00000000 000000000 01101001 => 105
//    00000000 00000000 000000000 00001101 => 13

      System.out.println(i >>> 3); // 00001101|0 => 13

//    00000000 00000000 000000000 01101001 => 105
//    00000000 00000000 000000000 00000110 => 6

      System.out.println(i >>> 4); // 00000110|1 => 6
      // 오른쪽으로 이동 후, 
      // 왼쪽 쪽 빈자리 : 원래 숫자와 같은 부로 값으로 채운다.
      //                  양수면 0, 음수면 1을 채운다.
      // 오른쪽 경계를 넘어간 비트 : 짜른다.

      // 음수를 이동
      
      i = 0b11111111_11111111_11111111_10101001; // -87
            
      System.out.println(i); 
      System.out.println(i >>> 1); 

//    11111111_11111111_11111111_10101001  => -87
//    01111111_11111111_11111111_11010100  =>  2147483604
      
      System.out.println(i >>> 2); 
//    11111111_11111111_11111111_10101001  => -87
//    00111111_11111111_11111111_11101010  =>  1073741802

      System.out.println(i >>> 3); 
//    11111111_11111111_11111111_10101001  => -87
//    00011111_11111111_11111111_11110101  =>  536870901
      
      System.out.println(i >>> 4);
//    11111111_11111111_11111111_10101001  => -87
//    00001111_11111111_11111111_11101010  =>  268435450
    }
}
