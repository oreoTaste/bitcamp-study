package com.eomcs.lms.web;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;
import com.eomcs.lms.util.RequestMapping;

@Component
@MultipartConfig(maxFileSize = 100000000)
public class MemberController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/add")
  public String addGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");
      return "/member/form.jsp";

    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }
  }


  @RequestMapping("/member/add")
  public String addPost(HttpServletRequest request, HttpServletResponse response) {

    try {
      Member member = new Member();

      request.setCharacterEncoding("UTF-8");
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setTel(request.getParameter("tel"));
      member.setRegisteredDate(new Date(System.currentTimeMillis()));

      Part photoPart = request.getPart("photo");
      if(photoPart.getSize() > 0) {
        String dirPath = request.getServletContext().getRealPath("/upload/member");
        String filename = UUID.randomUUID().toString();

        photoPart.write(dirPath + "/" + filename); // 여기서 문제 발생
        member.setPhoto(filename);
      }

      if(memberService.add(member)) {
        response.setContentType("text/html;charset=UTF-8");
        return "redirect:list";
      } else
        throw new Exception("멤버정보 등록이 불가합니다.(중복값 발생)");

    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }
  }

  @RequestMapping("/board/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html;charset=UTF-8");
    try {
      int no = Integer.parseInt(request.getParameter("no"));

      if(memberService.delete(no)) {
        response.setContentType("text/html;charset=UTF-8");
        return "redirect:list";
      } else
        throw new Exception("멤버정보 삭제에 실패했습니다.");

    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }
  }

  @RequestMapping("/board/detail")
  public String detail(ServletRequest request, ServletResponse response) {

    response.setContentType("text/html;charset=UTF-8");
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Member member = memberService.get(no);
      request.setAttribute("member", member);
      return "/member/detail.jsp";
    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }
  }

  @RequestMapping("/board/list")
  public String list(ServletRequest request, ServletResponse response) {

    try {
      response.setContentType("text/html;charset=UTF-8");
      request.setAttribute("list", memberService.list());
      return "/member/list.jsp";

    } catch(Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }
  }

  @RequestMapping("/board/search")
  public String search(ServletRequest request, ServletResponse response) {

    response.setContentType("text/html;charset=UTF-8");
    try {
      String keyword = request.getParameter("keyword");

      List<Member> members;
      members = memberService.search(keyword);
      request.setAttribute("members", members);
      return "/member/updateForm.jsp";
    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }
  }

  @RequestMapping("/member/update")
  public String updateGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    response.setContentType("text/html;charset=UTF-8");
    try {

      int no = Integer.parseInt(request.getParameter("no"));

      Member member = memberService.get(no);
      request.setAttribute("member", member);
      return "/member/updateform.jsp";
    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }
  }

  @RequestMapping("/member/update")
  public String updatePost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
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
        String dirPath = request.getServletContext().getRealPath("/upload/member");
        String filename = UUID.randomUUID().toString();
        
        photoPart.write(dirPath + "/" + filename);
        newMember.setPhoto(filename);
      }
      newMember.setTel(tel);

      if(memberService.update(newMember))
        return "redirect:list";
      else
        throw new Exception("멤버정보 수정에 실패했습니다.");

    } catch (Exception e) {
      request.setAttribute("errorMsg", e);
      return "list";
    }

  }
  
}
