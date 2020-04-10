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

@WebServlet("/member/search")
public class MemberSearchServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);
      
      request.getRequestDispatcher("/header").include(request, response);
      String keyword = request.getParameter("keyword");

      List<Member> members;
      members = memberService.search(keyword);
      if(!members.isEmpty()) {

        out.printf("<table border='1'>");
        out.printf("<tr>");
        out.printf("<th>멤버번호</th>");
        out.printf("<th>성함</th>");
        out.printf("<th>이메일</th>");
        out.printf("<th>전화번호</th>");
        out.printf("<th>등록일</th>");
        out.printf("</tr>");

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
      request.getRequestDispatcher("/footer").include(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}