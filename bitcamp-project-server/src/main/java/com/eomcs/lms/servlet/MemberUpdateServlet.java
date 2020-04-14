package com.eomcs.lms.servlet;

import java.io.IOException;
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
    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);

      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberService.get(no);
      request.setAttribute("member", member);
      request.setAttribute("viewUrl", "/member/updateform.jsp");

    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      request.setAttribute("errorUrl", "list");
    }
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
        request.setAttribute("viewUrl", "redirect:list");
      } else
        throw new Exception("멤버정보 수정에 실패했습니다.");


    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      request.setAttribute("errorUrl", "list");
    }

  }

  

}
