package com.eomcs.lms.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@WebServlet("/member/search")
public class MemberSearchServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);
      
      String keyword = request.getParameter("keyword");

      List<Member> members;
      members = memberService.search(keyword);
      if(!members.isEmpty()) {
        request.setAttribute("members", members);
        request.getRequestDispatcher("/member/updateForm.jsp").include(request, response);
      }
      
    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      request.setAttribute("errorUrl", "list");
    }
  }


}