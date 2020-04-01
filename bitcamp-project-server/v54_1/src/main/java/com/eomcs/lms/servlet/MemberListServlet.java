package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {
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

      out.println("<div><button onclick=\"location.href='../auth/loginForm'\">로그인</button'></div>");
      out.println("<div><form action='search'>");
      out.println("<input name='keyword' type=text size='70' value='검색할 멤버'>");
      out.println("<button>검색</button></div>");

      out.println("<a href='addForm'>멤버 추가</a><br>");

      List<Member> members = memberService.list();

      if(!members.isEmpty()) {
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>멤버번호</th>");
        out.println("<th>성함</th>");
        out.println("<th>이메일</th>");
        out.println("<th>전화번호</th>");
        out.println("<th>등록일</th>");
        out.println("</tr>");
        for (Member m : members) {

          out.printf("<tr>");
          out.printf("<td><a href='detail?no=%d'>%d</a></td>", m.getNo(), m.getNo());
          out.printf("<td><a href='detail?no=%d'>%s</a></td>", m.getNo(), m.getName());
          out.printf("<td><a href='detail?no=%d'>%s</a></td>", m.getNo(), m.getEmail());
          out.printf("<td><a href='detail?no=%d'>%s</a></td>", m.getNo(), m.getTel());
          out.printf("<td><a href='detail?no=%d'>%s</a></td>", m.getNo(), m.getRegisteredDate());
          out.printf("</tr>");
        }
      }
    } catch(Exception e) {

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
    out.println("<title> 멤버 목록 </title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 목록</h1>");
  }

}