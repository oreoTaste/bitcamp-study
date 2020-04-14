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

@WebServlet("/member/add")
@MultipartConfig(maxFileSize = 100000000)
public class MemberAddServlet extends HttpServlet {
  private static final long serialVersionUID =20200331;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");
      request.setAttribute("viewUrl", "/member/form.jsp");

    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      request.setAttribute("errorUrl", "list");
    }
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);

      Member member = new Member();

      request.setCharacterEncoding("UTF-8");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("viewUrl", "redirect:list");
      } else
        throw new Exception("멤버정보 등록이 불가합니다.(중복값 발생)");

    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      request.setAttribute("errorUrl", "list");
    }
  }

}
