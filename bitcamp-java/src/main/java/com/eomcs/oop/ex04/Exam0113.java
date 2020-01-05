// 생성자 활용 예 - 자바에서 제공하는 클래스 사용을 통해 생성자 활용을 익혀보자!
package com.eomcs.oop.ex04;

public class Exam0113 {

    public static void main(String[] args) throws Exception {


        byte[] bytes = {
            (byte)0x41, //A
            (byte)0x42, //B
            (byte)0x43, //C
            (byte)0x61, //a
            (byte)0x62, //b
            (byte)0x63, //c
            (byte)0x30, //0
            (byte)0x31, //1
            (byte)0x32, //2
            (byte)0x20, //space
            (byte)0x21, //!
            (byte)0x23, //#
            (byte)0x24, //$
            (byte)0x25, //%
            (byte)0x2b, //+
            (byte)0xea, (byte)0xb0, (byte)0x80, 
            (byte)0xea, (byte)0xb0, (byte)0x81 
            
            };
        
        String s5 = new String(bytes, "UTF8");
        System.out.println(s5);
    }
}

// 생성자의 활용
// => 인스턴스 변수를 초기화시키기 위해 여러 개의 생성자를 만들어 제공할 수 있다.
// => 자신에게 맞는 적절한 생성자를 호출하여 인스턴스를 초기화시킨 후 사용하면 된다. 















