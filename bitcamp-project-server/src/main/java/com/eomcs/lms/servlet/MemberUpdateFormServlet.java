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

@WebServlet("/member/updateForm")
public class MemberUpdateFormServlet extends GenericServlet {
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

      out.printf("<form action='update'>");
      out.printf("번호 : <input readonly name='no' type='text' value='%d'><br>", member.getNo());
      out.printf("성함: <input name='name' type='text' value='%s'><br>", member.getName());
      out.printf("이메일: <input name='email' type='text' value='%s'><br>", member.getEmail());
      out.printf("비밀번호: <input name='password' type='text' value='%s'><br>", member.getPassword());
      out.printf("사진: <input name='photo' type='text' value='%s'><br>", member.getPhoto());
      out.printf("전화번호: <input name='tel' type='text' value='%s'><br>", member.getTel());
      out.printf("등록일: <input readonly name='registeredDate' type='text' value='%1$tF %1$tH:%1$tM:%1$tS'><br>", member.getRegisteredDate());
      out.printf("<button>수정하기</button>", member.getTel());

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
    out.println("<title>멤버 정보 수정</title>");
    out.println("</head>");

    out.println("<body>");
    out.println("<h1>멤버 정보 수정</h1>");
  }

}
