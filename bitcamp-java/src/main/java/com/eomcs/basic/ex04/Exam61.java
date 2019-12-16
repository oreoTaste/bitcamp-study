package com.eomcs.basic.ex04;

public class Exam61 {
  public static void main(String[] args) {
    //배열 사용전
    int kor, eng, math, soc, mus;

    //배열 사용후
    int[] score = new int[5];

    //배열에 값을 저장하는 방법
    score[0] = 100;
    score[1] = 90;
    score[2] = 80;
    score[3] = 70;
    score[4] = 60;
    //score[-1] = 100; //실행오류(=runtime error) <-> 컴파일 에러
    //score[5] = 100; //실행오류(=runtime error) <-> 컴파일 에러

    // 배열 레퍼런스와 인스턴스를 따로 선언
    int[] arr1 = null; 
    //arr[0] = 100; // 실행오류(=runtime error) <-- 주소가0임. (=메모리를 가르키지 않음)
    arr1 = new int[5];
    arr1[0] = 100;
    arr1[1] = 100;

    System.out.println(arr1[0]);
    System.out.println(arr1[1]);
    int[] arr2;
    arr2 = arr1;
    System.out.println(arr2[0]);
    arr1[0] = 300;
    System.out.println(arr2[0]);
    int[] arr3 = new int[5];
    arr3[0] = 30;

    arr2 = arr3;
    System.out.println(arr2[0]);
    arr1 = arr2;
    System.out.println(arr1[0]);
    // 기존에 arr1에 저장되었던 기존 배열 인스턴스는 주소를 잃어버렸기때문에 접근불가
    // 이러한 접근물가 메모리를 "가비지(garbage)"라고 한다.
    // -> 메모리가 부족하면은! garbage collector에 의해 메모리에서 해제된다.
    // -> 전산학에서는 garbage = dangling object라고 부른다.

    int[] arr5 = new int[]{0,0,0,0,0,0};
    int[] arr6 = {0,0,0,0,0,0};
    int[] arr7;
    arr7 = new int[]{100,90,80};

  }
}
