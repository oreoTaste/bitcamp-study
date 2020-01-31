package com.eomcs.lms.domain;

import java.sql.Date;

public class Board {
  private int no;
  private String title;
  private Date date;
  private int viewCount;
  
  
  public String toCsvString() {
    
    return String.format("%1$d,%2$s,%3$tF %3$th:%3$tm:%3$ts,%4$d",
        this.getNo(),
        this.getTitle(),
        this.getDate(),
        this.getViewCount()
        );
  }
  
  public static Board valueOf(String[] data) {
    Board board = new Board();
    board.setNo(Integer.parseInt(data[0]));
    board.setTitle(data[1]);
    board.setDate(Date.valueOf(data[2]));
    board.setViewCount(Integer.parseInt(data[3]));
    return board;
  }
  
  
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
  
  @Override
  public boolean equals(Object obj) {
    if(obj.getClass() != this.getClass())
      return false;
    Board other = (Board)obj;
    
    if(this.no != other.no)
      return false;
    
    if(this.title != other.title)
      return false;
    return true;
  }
}
