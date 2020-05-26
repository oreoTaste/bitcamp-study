package com.eomcs.lms.web;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
public class AuthController {

  static Logger logger = LogManager.getLogger(AuthController.class);

  @Autowired
  MemberService memberService;

  @RequestMapping("auth/form")
  public String form(HttpServletRequest request, Map<String, Object> model) {
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
    model.put("email", email);
    return "auth/form";
  }

  @RequestMapping("auth/login")
  public String login(HttpServletRequest request, String email, String password, String saveEmail)
      throws Exception {
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
    return "auth/login";
  }

  @RequestMapping("/auth/logout")
  public String logout(HttpServletRequest request) {
    request.getSession().invalidate();
    return "redirect:../../index.html";
  }

  @SuppressWarnings("unchecked")
  @GetMapping("/auth/facebookLogin")
  public String facebookLogin(
      String accessToken, HttpSession session, Model model) throws Exception {

    // accessToken 값을 확인한다
    logger.info(accessToken);
    // accessToken을 가지고 페이스북 서버에 사용자 정보를 요청한다.
    // 가. JSON 또는 XML을 리턴하는 HTTP요청을 코딩하는 법

    // 1) Facebook Graph XML 사용하기
    // 스프링에서 제공하는 RestTemplate 클래스를 사용하라
    RestTemplate restTemplate = new RestTemplate();

    // 2) 서버에 요청하기
    Map<String, Object> response = restTemplate.getForObject("https://graph.facebook.com/v7.0/me?access_token={1}&fields={2}",
        Map.class, accessToken, "id,name,email");

    // 3) 사용자 정보 확인하기
    String email = (String) response.get("email");
    logger.info("페이스북 로그인 사용자 이메일 : {}", email);

    if(email == null) {
      // 엑세스 토큰이 무효하다면, 다시 로그인 입력폼으로 보낸다.
      session.invalidate();
      model.addAttribute("refreshUrl", "2;url=form");
      logger.info("페이스북 엑세스 토큰이 무효함");
      return "auth/login";
    }

    // 페이스북에 정상적으로 로그인 되었다면
    // 현재 서버에 등록된 사용자를 이메일로 찾는다.
    Member member = memberService.get(email);
    if(member == null) {
      // 현재 서버에 등록되어 있지 않다면, 새 사용자로 자동 등록한다.
      member = new Member();
      member.setName(""); // 이름
      member.setEmail(email); // 이메일
      member.setPassword(UUID.randomUUID().toString()); // 암호
      memberService.add(member);
      logger.info("페이스북 사용자를 회원으로 등록함 : {}" + member);
    }
    
    logger.info("세션에 로그인 사용자 정보를 보관함");
    session.setAttribute("loginUser", member);
    model.addAttribute("refreshUrl", "2;url=../../index.html");
    return "auth/login";
  }





}
