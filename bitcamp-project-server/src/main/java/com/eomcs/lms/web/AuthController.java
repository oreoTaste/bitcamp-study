package com.eomcs.lms.web;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;
import com.eomcs.lms.util.RequestMapping;

@Component
public class AuthController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/auth/login")
  public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      String email = "";
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
        for (Cookie cookie : cookies) {
          if (cookie.getName().equals("email")) {
            email = cookie.getValue();
            break;
          }
        }
      }
      request.setAttribute("email", email);
      return "/auth/form.jsp";
    }

    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String saveEmail = request.getParameter("saveEmail");

    Cookie cookie = new Cookie("email", email);
    if (saveEmail != null) {
      cookie.setMaxAge(60 * 60 * 24 * 30);
    } else {
      cookie.setMaxAge(0);
    }

    @SuppressWarnings("unchecked")
    ArrayList<Cookie> cookies = (ArrayList<Cookie>) request.getAttribute("cookies");
    cookies.add(cookie);

    Member member = memberService.get(email, password);
    if (member != null) {
      request.getSession().setAttribute("loginUser", member);
      request.setAttribute("refreshUrl", "2;url=../../index.html");
    } else {
      request.getSession().invalidate();
      request.setAttribute("refreshUrl", "2;url=login");
    }

    return "/auth/login.jsp";
  }

  @RequestMapping("/auth/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getSession().invalidate();
    return "redirect:../../index.html";
  }
}
