//#캡슐화 문법 사용 전 : 개발자가 클래스를 작성한 사람의 의도대로 정상적으로 사용할때

package com.eomcs.oop.ex07.a;

class Score2 {
  String name;
  private int kor;
  private int eng;
  private int math;
  private int sum;
  private float aver;

  void compute() {
    this.sum = this.kor + this.math + this.eng;
    this.aver = this.sum / 3f;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setKor(int kor) {
    this.kor = kor;
    this.compute();
  }

  public void setEng(int eng) {
    this.eng = eng;
    this.compute();
  }

  public void setMath(int math) {
    this.math = math;
    this.compute();
  }

  public String getName() {
    return this.name;
  }

  public int getKor() {
    return this.kor;
  }

  public int getEng() {
    return this.eng;
  }

  public int getMath() {
    return this.math;
  }

  public int getSum() {
    return sum;
  }

  public float getAver() {
    return aver;
  }

}

public class Exam0311 {
  public static void main(String[] args) {
    Score s1 = new Score();
    s1.setName("홍길동");
    s1.setKor(100);
    s1.setEng(90);
    s1.setMath(85);

    System.out.printf("%s, %d, %d, %d, %d, %.1f\n",
        s1.getName(), s1.getKor(), s1.getEng(), s1.getMath(), s1.getSum(), s1.getAver());
  }
}
