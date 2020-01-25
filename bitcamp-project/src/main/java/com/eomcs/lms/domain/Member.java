package com.eomcs.lms.domain;

import java.sql.Date;

public class Member {
  private int no;
  private String name;
  private String email;
  private String password;
  private String photo;
  private String tel;
  private Date registeredDate;

  public static Member valueOf(String line) {
    String[] data = line.split(",");

    Member member = new Member();
    member.setNo(Integer.parseInt(data[0]));
    member.setName(data[1]);
    member.setEmail(data[2]);
    member.setPassword(data[3]);
    member.setPhoto(data[4]);
    member.setTel(data[5]);
    member.setRegisteredDate(Date.valueOf(data[6]));
    return member;
  }


  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s\n",
        getNo(),
        getName(),
        getEmail(),
        getPassword(),
        getPhoto(),
        getTel(),
        getRegisteredDate());
  }


  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj.getClass() != Member.class) {
      return false;
    }

    Member other = (Member)obj;

    if(no != other.no) {
      return false;
    }

    if(name.equals(other.name)) {
      return false;
    }

    if(email.equals(other.email)) {
      return false;
    }

    if(password.equals(other.password)) {
      return false;
    }

    if(photo.equals(other.photo)) {
      return false;
    }

    if(tel.equals(other.tel)) {
      return false;
    }

    if(registeredDate != other.registeredDate) {
      return false;
    }
    return true;
  }
}
