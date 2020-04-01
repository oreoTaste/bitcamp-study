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
import com.eomcs.lms.service.MemberService;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends GenericServlet {
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

      if(memberService.delete(no)) {
        out.println("회원을 삭제했습니다.");
      } else {
        out.println("해당 번호의 회원이 없습니다");
      }

    } catch (Exception e) {

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
    out.println("<meta http-equiv='refresh' content=\"2; url='list'\">");
    out.println("<title> 멤버 삭제 </title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 삭제</h1>");
  }

}
