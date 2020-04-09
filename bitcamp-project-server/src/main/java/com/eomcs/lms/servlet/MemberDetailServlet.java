package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
    PrintWriter out = response.getWriter();
    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =(ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);
      
      request.getRequestDispatcher("/header").include(request, response);
      out.println("<h1>멤버 세부정보</h1>");

      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberService.get(no);
      if(member==null) {
        out.printf("<p>해당 멤버를 찾을 수 없습니다.</p>");
      }
      else {
        out.printf("<table border='1'>");
        out.printf("<tr>");
        out.printf("<th>번호</th>");
        out.printf("<th>이름</th>");
        out.printf("<th>이메일</th>");
        out.printf("<th>암호</th>");
        out.printf("<th>사진</th>");
        out.printf("<th>전화</th>");
        out.printf("<th>등록일</th>");
        out.printf("</tr>");

        out.printf("<tr>");
        out.printf("<td>%d</td>", member.getNo());
        out.printf("<td>%s</td>", member.getName());
        out.printf("<td>%s</td>", member.getEmail());
        out.printf("<td>%s</td>", member.getPassword());
        out.printf("<td>%s</td>", member.getPhoto());
        out.printf("<td>%s</td>", member.getTel());
        out.printf("<td>%1$tF %1$tH:%1$tM:%1$tS</td>", member.getRegisteredDate());
        out.printf("</tr>");

        out.println("<br>");
        out.printf("<form action='delete' method='post'>");
        out.printf("<button name='no' value='%d'>삭제</button>  ..  ",member.getNo());
        out.printf("</form>");
        out.printf("<button onclick=\"location.href='update?no=%d'\">수정</button>  ..  ",member.getNo());
        out.printf("<button onclick=\"location.href='list'\">수업정보로 돌아가기</button>");
      }
    } catch (Exception e) {
      out.println("멤버 정보 수신중 오류발생!");
    }

    request.getRequestDispatcher("/footer").include(request, response);
  }


}
