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

@WebServlet("/member/update")
@MultipartConfig(maxFileSize = 5000000)
public class MemberUpdateServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);

      request.getRequestDispatcher("/header").include(request, response);
      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberService.get(no);

      out.printf("<form action='update' method='post' enctype='multipart/form-data'>");
      out.printf("번호 : <input readonly name='no' type='text' value='%d'><br>", member.getNo());
      out.printf("성함: <input name='name' type='text' value='%s'><br>", member.getName());
      out.printf("이메일: <input name='email' type='text' value='%s'><br>", member.getEmail());
      out.printf("비밀번호: <input name='password' type='text' value='%s'><br>", member.getPassword());
      out.printf("사진: <input name='photo' type='file' value='%s'><br>", member.getPhoto());
      out.printf("전화번호: <input name='tel' type='text' value='%s'><br>", member.getTel());
      out.printf("등록일: <input readonly name='registeredDate' type='text' value='%1$tF %1$tH:%1$tM:%1$tS'><br>", member.getRegisteredDate());
      out.printf("<button>수정하기</button>", member.getTel());

    } catch (Exception e) {
    }
    request.getRequestDispatcher("/footer").include(request, response);
  }
  
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    ServletContext servletContext = request.getServletContext();
    ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
    MemberService memberService = iocContainer.getBean(MemberService.class);
    int no = Integer.parseInt(request.getParameter("no"));

    try {

      String name = request.getParameter("name");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String tel  = request.getParameter("tel");

      Member newMember = new Member();

      newMember.setNo(no);
      newMember.setRegisteredDate(new Date(System.currentTimeMillis()));
      newMember.setName(name);
      newMember.setEmail(email);
      newMember.setPassword(password);

      Part photoPart = request.getPart("photo");
      if(photoPart.getSize() > 0) {
        String dirPath = getServletContext().getRealPath("/upload/member");
        String filename = UUID.randomUUID().toString();
        
        photoPart.write(dirPath + "/" + filename);
        newMember.setPhoto(filename);
      }
      
      newMember.setTel(tel);

      if(memberService.update(newMember)) {
        response.sendRedirect("list");
      } else
        throw new Exception("멤버정보 수정에 실패했습니다.");


    } catch (Exception e) {
      request.getSession().setAttribute("errorMsg", e);
      request.getSession().setAttribute("errorUrl", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }

  }

  

}
