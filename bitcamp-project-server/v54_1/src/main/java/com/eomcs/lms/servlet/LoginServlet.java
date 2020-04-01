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

@WebServlet("/auth/login")
public class LoginServlet extends GenericServlet {
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

      String email = req.getParameter("email");
      String password = req.getParameter("password");
      Member member = memberService.get(email, password);

      if(member == null) {
        out.println("사용자 정보가 유효하지 않습니다.");
      } else {
        out.println(String.format("'%s'님 반갑습니다.", member.getName()));
      }
    } catch(Exception e) {
      out.println("사용자 정보가 유효하지 않습니다.");
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
    out.println("<meta http-equiv='refresh' content=\"3; url='../member/list'\">");
    out.println("<title>멤버 정보 수정</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 정보 수정</h1>");
  }

}
