package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;


@WebServlet("/member/detail")
public class MemberDetailServlet extends GenericServlet {
  private static final long serialVersionUID =20200331;

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);

      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberService.get(no);
      if(member!=null) {
        request.setAttribute("member", member);
        request.setAttribute("viewUrl", "/member/detail.jsp");
      }
    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      request.setAttribute("errorUrl", "list");
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }


}
