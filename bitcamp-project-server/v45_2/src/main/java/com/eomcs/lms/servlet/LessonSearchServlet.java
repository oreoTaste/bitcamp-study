package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.Prompt;

public class LessonSearchServlet implements Servlet {

  LessonService lessonService;
  
  public LessonSearchServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }
  
  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    HashMap<String, Object> map = new HashMap<>();
    
    String keyword = Prompt.getString(in, out, "수업명 검색(xx포함)?");
    if(keyword.length() > 0) {
      map.put("title", keyword);
    }
    
//    conts는 뺌 : lesson.setContext(Prompt.getString(in, out, "수업내용? "));
    
    Date date = Prompt.getDate(in, out, "시작일 검색(xx이후)?");
    if(date != null) {
      map.put("startDate", date);
    }
    
    date = Prompt.getDate(in, out, "종료일 검색 (xx이전)?");
    if(date != null) {
      map.put("endDate", date);
    }
    
    int hour = Prompt.getInt(in, out, "총 수업시간 검색 (xx이내)?");
    if(hour > 0) {
      map.put("totalHour", hour);
    }
    
    hour = Prompt.getInt(in, out, "일 수업시간 검색 (xx이내)?");
    if(hour > 0) {
      map.put("dailyHour", hour);
    }
    
    List<Lesson> lesson = lessonService.search(map);
    
    out.println("------------------");
    out.println("검색결과 :");
    for (Lesson ls : lesson) {
      out.printf("%d, \t%-8s, \t%s, %tF ~ %tF, %d, %d\n",
      ls.getNo(),
      ls.getTitle(),
      ls.getContext(),
      ls.getStartDate(),
      ls.getEndDate(),
      ls.getTotalHour(),
      ls.getDailyHour());
    }
  }

}
