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

@WebServlet("/member/update")
public class MemberUpdateServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    ServletContext servletContext = req.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    MemberService memberService = iocContainer.getBean(MemberService.class);
    int no = Integer.parseInt(req.getParameter("no"));

    try {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<meta http-equiv='refresh' content=\"2; url='detail?no=%d'\">", no);
      out.println("<title>멤버 정보 수정</title>");
      out.println("</head>");

      out.println("<body>");
      out.println("<h1>멤버 정보 수정</h1>");
    } catch(Exception e) {

    }

    try {

      String name = req.getParameter("name");
      String email = req.getParameter("email");
      String password = req.getParameter("password");
      String photo = req.getParameter("photo");
      String tel  = req.getParameter("tel");

      Member newMember = new Member();

      newMember.setNo(no);
      newMember.setRegisteredDate(new Date(System.currentTimeMillis()));
      newMember.setName(name);
      newMember.setEmail(email);
      newMember.setPassword(password);
      newMember.setPhoto(photo);
      newMember.setTel(tel);

      memberService.update(newMember);

      out.println("회원을 변경했습니다.");

    } catch (Exception e) {
      out.println("멤버 정보 수정중 오류발생");
    }

    printTail(out);

  }

  private void printTail(PrintWriter out) {
    out.println("</body>");
    out.println("</html>");
  }


}
