package com.eomcs.lms.domain;

import java.sql.Date;

public class Lesson {
  private int no;
  private String title;
  private Date date;
  private int viewCount;
  private String context;
  private Date startDate;
  private Date endDate;
  private int totalHour;
  private int dailyHour;
  
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
  public String getContext() {
    return context;
  }
  public void setContext(String context) {
    this.context = context;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public int getTotalHour() {
    return totalHour;
  }
  public void setTotalHour(int totalHour) {
    this.totalHour = totalHour;
  }
  public int getDailyHour() {
    return dailyHour;
  }
  public void setDailyHour(int dailyHour) {
    this.dailyHour = dailyHour;
  }
  
  @Override
  public boolean equals(Object obj) {
    if(obj.getClass() != Lesson.class) 
      return false;
    
    Lesson other = (Lesson) obj;
    
    if(this.no != other.no)
      return false;
    
    if(this.title.equals(other.title))
      return false;

    if(this.date != other.date)
      return false;
    
    if(this.viewCount != other.viewCount)
      return false;
    
    if(this.context.equals(other.context))
      return false;
    
    if(this.startDate != other.startDate)
      return false;
    
    if(this.endDate != other.endDate)
      return false;
    
    if(this.totalHour != other.totalHour)
      return false;
    
    if(this.dailyHour != other.dailyHour)
      return false;
    
    return true;
  }
}
