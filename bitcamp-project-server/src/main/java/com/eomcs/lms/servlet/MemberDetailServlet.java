package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@WebServlet("/member/detail")
public class MemberDetailServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    try {
      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);
      printHead(out);

      int no = Integer.parseInt(req.getParameter("no"));

      Member member = memberService.get(no);
      if(member==null) {
        out.printf("<p>해당 멤버를 찾을 수 없습니다.</p>");
      }
      else {
        out.printf("<table border='1'>");
        out.printf("<tr>");
        out.printf("<th>번호</th>");
        out.printf("<th>이름</th>");
        out.printf("<th>이메일</th>");
        out.printf("<th>암호</th>");
        out.printf("<th>사진</th>");
        out.printf("<th>전화</th>");
        out.printf("<th>등록일</th>");
        out.printf("</tr>");

        out.printf("<tr>");
        out.printf("<td>%d</td>", member.getNo());
        out.printf("<td>%s</td>", member.getName());
        out.printf("<td>%s</td>", member.getEmail());
        out.printf("<td>%s</td>", member.getPassword());
        out.printf("<td>%s</td>", member.getPhoto());
        out.printf("<td>%s</td>", member.getTel());
        out.printf("<td>%1$tF %1$tH:%1$tM:%1$tS</td>", member.getRegisteredDate());
        out.printf("</tr>");

        out.println("<br>");
        out.printf("<button onclick=\"location.href='delete?no=%d'\">삭제</button>  ..  ",member.getNo());
        out.printf("<button onclick=\"location.href='updateForm?no=%d'\">수정</button>  ..  ",member.getNo());
        out.printf("<button onclick=\"location.href='list'\">수업정보로 돌아가기</button>");
      }
    } catch (Exception e) {
      out.println("멤버 정보 수신중 오류발생!");
    }

    printTail(out);
  }

  private void printTail(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
  }

  private void printHead(PrintWriter out) {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title> 멤버 세부정보 </title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 세부정보</h1>");
  }

}
