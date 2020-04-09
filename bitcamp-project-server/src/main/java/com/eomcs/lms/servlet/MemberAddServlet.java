package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      request.getRequestDispatcher("/header").include(request, response);
      out.printf("<form action='add' method='post'>");
      out.printf("성함: <input name='name' type='text'><br>");
      out.printf("이메일: <input name='email' type='text'><br>");
      out.printf("비밀번호: <input name='password' type='text'><br>");
      out.printf("사진: <input name='photo' type='text'><br>");
      out.printf("전화번호: <input name='tel' type='text'><br>");
      out.printf("<button>등록</button>");

      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("멤버 추가 저장 중 오류발생");
    }
    request.getRequestDispatcher("/footer").include(request, response);
    }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    
    try {
    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    MemberService memberService = iocContainer.getBean(MemberService.class);

    Member member = new Member();

    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setPhoto(request.getParameter("photo"));
    member.setTel(request.getParameter("tel"));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    if(memberService.add(member)) {
      response.sendRedirect("list");
    } else
      throw new Exception("멤버정보 등록이 불가합니다.(중복값 발생)");

    } catch (Exception e) {
      request.getSession().setAttribute("errorMsg",e);
      request.getSession().setAttribute("errorUrl","list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }

}
