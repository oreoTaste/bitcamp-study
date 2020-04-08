// 서블릿 만들기 - @WebServlet 애노테이션
package com.eomcs.web.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

// @WebServlet 애노테이션
// => web.xml 에 서블릿 정보를 설정하는 대신에
//    이 애노테이션을 사용하여 서블릿을 설정할 수 있다.
// => 이 애노테이션을 활성화시키려면
//    web.xml의 <web-app> 태그에 다음 속성을 추가해야 한다.
//      metadata-complete="false"
//    속성의 값은 false 여야 한다.
//
@WebServlet("/ex01/s04")
public class Servlet04 extends GenericServlet {
  
  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("</head>");
    out.println("<body>");
    out.println("wow");
    out.println("</body>");
    out.println("</html>");
    System.out.println("Servlet04.service(ServletRequest,ServletResponse)");
  }
}






