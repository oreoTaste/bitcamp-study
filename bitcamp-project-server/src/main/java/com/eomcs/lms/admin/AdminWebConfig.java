package com.eomcs.lms.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
// Spring IoC 컨테이너가 이 클래스를 Java Config로 자동인식 하려면
// 다음 태그를 붙여야한다. 단, 이 클래스가 @ComponentScan에서 지정한
// 패키지 안에 있어야한다.
// @Configuration
@ComponentScan("com.eomcs.lms.admin")
@EnableWebMvc
public class AdminWebConfig {
  
  static Logger logger = LogManager.getLogger(AdminWebConfig.class);
  
  public AdminWebConfig() {
    logger.debug("AdminWebConfig 객체 생성!");
  }
  
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver(
        "/WEB-INF/admin/", ".jsp");
    return vr;
  }
  
  @Bean
  public MultipartResolver multipartResolver() {
    // 스프링 방식으로 파일 업로드를 처리하고 싶다면,
    // 이 메서드를 통해 MultipartResolver 구현체를 생성해야 한다.
    CommonsMultipartResolver mr = new CommonsMultipartResolver();
    mr.setMaxUploadSize(10000000);
    mr.setMaxInMemorySize(2000000);
    mr.setMaxUploadSizePerFile(5000000);
    return mr;
  }
  
}









