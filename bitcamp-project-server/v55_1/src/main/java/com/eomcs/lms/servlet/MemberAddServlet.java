package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@WebServlet("/member/add")
@MultipartConfig(maxFileSize = 100000000)
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
      
      out.printf("<form action='add' method='post' enctype='multipart/form-data'>");
      out.printf("<table>");
      out.printf("<tr>");
      out.printf("<td>성함</td>");
      out.printf("<td><input name='name' type='text' style='width:300px'></td>");
      out.printf("</tr>");
      out.printf("<tr>");
      out.printf("<td>이메일</td>");
      out.printf("<td><input name='email' type='text' style='width:300px'></td>");
      out.printf("</tr>");
      out.printf("<tr>");
      out.printf("<td>비밀번호</td>");
      out.printf("<td><input name='password' type='text' style='width:300px'></td>");
      out.printf("</tr>");
      out.printf("<tr>");
      out.printf("<td>사진</td>");
      out.printf("<td><input name='photo' type='file' style='width:300px'></td>");
      out.printf("</tr>");
      out.printf("<tr>");
      out.printf("<td>전화번호</td>");
      out.printf("<td><input name='tel' type='text' style='width:300px'></td>");
      out.printf("</tr>");
      out.printf("</table>");
      out.printf("<button>등록</button>");
      out.printf("</form>");

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
    member.setTel(request.getParameter("tel"));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    
    Part photoPart = request.getPart("photo");
    if(photoPart.getSize() > 0) {
      String dirPath = getServletContext().getRealPath("/upload/member");
      String filename = UUID.randomUUID().toString();
      
      photoPart.write(dirPath + "/" + filename); // 여기서 문제 발생
      member.setPhoto(filename);
    }
    
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
