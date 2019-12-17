// 비트 이동 연산자 : >>, >>>, << 
package com.eomcs.basic.ex05;

public class Exam04_2 {
    public static void main(String[] args) {
//      >> 비트이동 연산자 사용법
      
//      => 왼쪽 빈자리는 숫자의 부호비트로 채운다.
//         음수니까 1로 채운다.
//      => 2로 나눈 후에 소수점이 있으면 
//         그 수보다 더 작은 정수값이 되는 결과가 나온다.
      
        int i = 0b01101001; // 105
//      00000000 00000000 000000000 01101001 => 105
//      00000000 00000000 000000000 00110100 => 52
        System.out.println(i); 
        System.out.println(i >> 1); // 00110100|1 => 52

//      00000000 00000000 000000000 01101001 => 105
//      00000000 00000000 000000000 00011010 => 26

        System.out.println(i >> 2); // 00011010|0 => 26

//      00000000 00000000 000000000 01101001 => 105
//      00000000 00000000 000000000 00001101 => 13

        System.out.println(i >> 3); // 00001101|0 => 13

//      00000000 00000000 000000000 01101001 => 105
//      00000000 00000000 000000000 00000110 => 6

        System.out.println(i >> 4); // 00000110|1 => 6
        // 오른쪽으로 이동 후, 
        // 왼쪽 쪽 빈자리 : 원래 숫자와 같은 부로 값으로 채운다.
        //                  양수면 0, 음수면 1을 채운다.
        // 오른쪽 경계를 넘어간 비트 : 짜른다.

        // 음수를 이동
        
        i = 0b11111111_11111111_11111111_10101001; // -87
        
//          11111111_11111111_11111111_10101001  => -87
//          11111111_11111111_11111111_11010100  => -44
//          00000000_00000000_00000000_00101100  (2의보수)
        
        System.out.println(i); 
        System.out.println(i >> 1); 
        // 1_11111111_11111111_11111111_1010100|1   => -44 


//      11111111_11111111_11111111_10101001  => -87
//      11111111_11111111_11111111_11101010  => -22
        
        System.out.println(i >> 2); 
        // 11_11111111_11111111_11111111_101010|01 => -22

        System.out.println(i >> 3); 
        // 111_11111111_11111111_11111111_10101|001 => -11
        
        System.out.println(i >> 4);
        // 1111_11111111_11111111_11111111_1010|1001 => -6
    }
}

// 오른쪽 이동
// - 1비트 이동은 나누기 2 한 것과 같은 효과를 준다.
// - 소수점 이하는 짤리는 효과를 갖는다.(더 작은 숫자로 된다)
// - -2.5의 경우 -3이 된다.