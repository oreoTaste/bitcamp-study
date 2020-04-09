package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    request.getRequestDispatcher("/header").include(request, response);

    out.printf("<h2>로그인 화면입니다.</h2>");
    out.printf("<form action='login' method='post'>");
    out.printf("<table border='1'>");
    out.printf("<tr><th>이메일</th><td><input name='email'></td><td rowspan='2'><button style='line-height: 4'>로그인</button</td></tr>");
    out.printf("<tr><th>비밀번호</th><td><input type='password' name='password'></td></tr>");

    request.getRequestDispatcher("/footer").include(request, response);
    
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);

      String refreshUrl = "../index.html";
      request.setAttribute("refreshUrl", refreshUrl);
      request.getRequestDispatcher("/header").include(request, response);


      String email = request.getParameter("email");
      String password = request.getParameter("password");
      Member member = memberService.get(email, password);

      if(member == null) {
        out.println("사용자 정보가 유효하지 않습니다.");
      } else {
        out.println(String.format("'%s'님 반갑습니다.", member.getName()));
      }
    } catch(Exception e) {
      out.println("사용자 정보가 유효하지 않습니다.");
    }

    request.getRequestDispatcher("/footer").include(request, response);
  }


}
