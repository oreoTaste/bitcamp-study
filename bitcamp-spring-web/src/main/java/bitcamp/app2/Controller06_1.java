// 스프링 예외 다루기
package bitcamp.app2;

import java.io.IOException;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/c06_1")
public class Controller06_1 {

  // 테스트:
  //   http://.../app2/c06_1/get
  @GetMapping("get")
  public void get() {}
  
  // 테스트:
  //   http://.../app2/c06_1/post
  @PostMapping("post")
  public void post() {}

  // 테스트:
  //   http://.../app2/c06_1/error
  @GetMapping("error1")
  public void error1() throws Exception {
    throw new Exception("request handler 오류 발생!");
    // 예외 던질때 처리방법
    // 1) 페이지 컨트롤러 안에 예외 처리기가 있다면, 실행
    // 2) 글로벌 컨트롤러 안에 예외 처리기가 있다면, 실행
    // 3) web.xml에 지정된 예외 처리 기본페이지를 실행
    // 4) 서블릿 컨테이너의 기본 오류 처리 페이지를 실행한다.
  }
  
  // 테스트:
  //   http://.../app2/c06_1/error
  @GetMapping("error2")
  public void error2() throws Exception {
    throw new IOException("request handler 오류 발생!");
  }

  // 테스트:
  //   http://.../app2/c06_1/error
  @GetMapping("error3")
  public void error3() throws Exception {
    throw new SQLException("request handler 오류 발생!");
  }
  
  @ExceptionHandler
  public ModelAndView exceptionHandler(Exception ex) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("error", ex);
    mv.setViewName("error6");
    return mv;
  }
}













