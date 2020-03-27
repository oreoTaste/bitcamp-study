package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.RequestMapping;

@Component
public class LessonDetailServlet {

  LessonService lessonService;
  
  public LessonDetailServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }
  
  @RequestMapping("/lesson/detail")
  public void service(Map<String, String> map, PrintStream out) throws Exception {
    
    printHead(out);
    Lesson lesson = null;
    try {
      lesson = lessonService.get(Integer.parseInt(map.get("no")));
      
      out.printf("번호? %d<br>", lesson.getNo());
      out.printf("수업명: %s<br>", lesson.getTitle());
      out.printf("수업내용: %s<br>", lesson.getContext());
      out.printf("기간 : %tF ~ %tF<br>", lesson.getStartDate(), lesson.getEndDate());
      out.printf("일수업시간: %d<br>", lesson.getTotalHour());
      out.printf("일수업시간: %d<br>", lesson.getDailyHour());
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("수업정보 세부사항 수신 중 오류발생");
    }
    
    
    
    out.printf("<button type='button' onclick=location.href='/photoboard/list?lessonNo=%d'>사진게시판</button>  ..  ", lesson.getNo());
    
    out.printf("<button type='button' onclick=location.href='/lesson/delete?no=%d'>삭제</button>  ..  ", lesson.getNo());
    out.printf("<button type='button' onclick=location.href='/lesson/updateForm?no=%d'>변경</button>  ..  ", lesson.getNo());
    out.printf("<button type='button' onclick=location.href='/lesson/list'>수업 목록으로 돌아가기</button>");
    printTail(out);

  }
  
  private void printTail(PrintStream out) {
    out.println("</body>");
    out.println("</html>");
  }

  private void printHead(PrintStream out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title> 수업 목록 </title>");
    out.println("</head>");

    out.println("<body>");
  }
  
}
