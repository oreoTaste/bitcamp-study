// 서블릿 초기화 파라미터 - 애노테이션으로 설정하기
package com.eomcs.web.ex06;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

// 서블릿이 사용할 값을 DD 설정으로 지정할 수 있다.
//
@WebServlet(
    value="/ex06/s3",
    loadOnStartup=1
    ,initParams={
        @WebInitParam(name="jdbc.driver", value="org.mariadb.jdbc.Driver"),
        @WebInitParam(name="jdbc.url", value="jdbc:mariadb://localhost/bitcampdb"),
        @WebInitParam(name="jdbc.username", value="bitcamp"),
        @WebInitParam(name="jdbc.password", value="1111")
    }
    )
@SuppressWarnings("serial")
public class Servlet03 extends HttpServlet {

  //  @Override
  //  public void init(ServletConfig config) throws ServletException {
  //    super.init(config);
  //  }

  @Override
  public void init() throws ServletException {
    System.out.println("/ex06/s3 -> init()");
    // 이 객체가 생성될 때 DB에 연결된다고 가정!
    // 서블릿 DD 설정 값을 꺼내려면 ServletConfig 객체가 있어야 한다.
    
//    String jdbcDriver = "jdbc.driver";
//    String jdbcUrl = "jdbc:mariadb://localhost:3306/studydb";
//    String username = "jdbc.username";
//    String password = "jdbc.password";
    
    ServletConfig servletConfig = this.getServletConfig();
    String jdbcDriver = servletConfig.getInitParameter("jdbc.driver");
    String jdbcUrl = servletConfig.getInitParameter("jdbc.url");
    String username = servletConfig.getInitParameter("jdbc.username");
    String password = servletConfig.getInitParameter("jdbc.password");
    
    System.out.println(jdbcDriver);
    System.out.println(jdbcUrl);
    System.out.println(username);
    System.out.println(password);
    
  }
}

