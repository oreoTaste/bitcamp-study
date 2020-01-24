package com.eomcs.lms.domain;

import java.sql.Date;

public class Board {
  private int no;
  private String title;
  private Date date;
  private int viewCount;
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public int getViewCount() {
    return viewCount;
  }
  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
  
  public boolean equals(Object obj) {
    if(obj.getClass() != this.getClass()) {
      return false;
    }
    Board other = (Board)obj;
    
    if(this.no != other.no) {
      return false;
    }
    
    if(this.title != other.title) {
      return false;
    }
    return true;
  }
}
