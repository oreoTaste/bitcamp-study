package com.eomcs.lms;

import java.sql.Date;

// 게시물 데이터를 저장할 새로운 형태의 메모리를 설계
public class Board {
  int no;
  String title;
  Date date;
  int viewCount;
  
  String context;
  java.util.Date startDate;
  java.util.Date endDate;
  int totalHour;
  int dailyHour;
  String name;
  String email;
  String password;
  String photo;
  String tel;
  String registeredDate;
}
