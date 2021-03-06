package com.eomcs.lms.web;

import java.io.File;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {

  @Autowired
  ServletContext servletContext;

  @Autowired
  MemberService memberService;

  @GetMapping("form")
  public void form() {}

  @PostMapping("add")
  public String add(Member member, MultipartFile photoFile) throws Exception {
    if (photoFile.getSize() > 0) {
      String dirPath = servletContext.getRealPath("/upload/member");
      String filename = UUID.randomUUID().toString();
      photoFile.transferTo(new File(dirPath + "/" + filename));
      member.setPhoto(filename);
    }

    if (memberService.add(member))
      return "redirect:list";
    else
      throw new Exception("회원을 추가할 수 없습니다.");
  }

  @GetMapping("delete")
  public String delete(int no) throws Exception {
    if (memberService.delete(no))
      return "redirect:list";
    else
      throw new Exception("삭제할 회원 번호가 유효하지 않습니다.");
  }

  @GetMapping("detail")
  public void detail(int no, Model model) throws Exception {
    Member member = memberService.get(no);
    model.addAttribute("member", member);
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("list", memberService.list());
  }

  @GetMapping("search")
  public void search(String keyword, Model model) throws Exception {
    model.addAttribute("list", memberService.search(keyword));
  }

  @PostMapping("update")
  public String update(Member member, MultipartFile photoFile) throws Exception {
    if (photoFile.getSize() > 0) {
      String dirPath = servletContext.getRealPath("/upload/member");
      String filename = UUID.randomUUID().toString();
      photoFile.transferTo(new File(dirPath + "/" + filename));
      member.setPhoto(filename);
    }

    if (memberService.update(member))
      return "redirect:list";
    else
      throw new Exception("변경할 회원 번호가 유효하지 않습니다.");
  }
}
















