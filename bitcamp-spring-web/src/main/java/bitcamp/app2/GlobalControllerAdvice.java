// 스프링 예외 다루기
package bitcamp.app2;

import java.io.IOException;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {


  // request handler가 던지는 예외에 따라 메서드 호출한다
  // @ExceptionHandler가 붙어있어야한다.
  @ExceptionHandler
  public ModelAndView exceptionHandler(Exception ex) {
    ModelAndView mv = new ModelAndView();
    if(ex.getMessage().contains("했습니다")) {
      mv.addObject("error", ex);
      mv.setViewName("error3");
      return mv;
    } else
      return null;
  }

  @ExceptionHandler
  public ModelAndView ioExceptionHandler(IOException ex) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("error", ex);
    mv.setViewName("error4");
    return mv;
  }

  @ExceptionHandler
  public ModelAndView sqlExceptionHandler(SQLException ex) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("error", ex);
    mv.setViewName("error5");
    return mv;
  }

}













