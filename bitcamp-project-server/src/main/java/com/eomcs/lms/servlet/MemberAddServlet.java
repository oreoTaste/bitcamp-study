package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@WebServlet("/member/add")
public class MemberAddServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    ServletContext servletContext = req.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    MemberService memberService = iocContainer.getBean(MemberService.class);

    printHead(out);
    Member member = new Member();

    member.setName(req.getParameter("name"));
    member.setEmail(req.getParameter("email"));
    member.setPassword(req.getParameter("password"));
    member.setPhoto(req.getParameter("photo"));
    member.setTel(req.getParameter("tel"));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    try {
      memberService.add(member);
      out.println("저장하였습니다.");

    } catch (Exception e) {
      out.println("멤버 추가 중복값이 있어 등록이 불가합니다.");
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
    out.println("<meta >");
    out.println("<title>멤버 추가</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 추가</h1>");
  }
}
