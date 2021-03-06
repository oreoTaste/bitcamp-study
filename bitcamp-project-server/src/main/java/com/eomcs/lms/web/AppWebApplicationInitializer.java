package com.eomcs.lms.web;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.eomcs.lms.AppConfig;

public class AppWebApplicationInitializer
  extends AbstractAnnotationConfigDispatcherServletInitializer {

//  String uploadTmpDir;
//
//  public AppWebApplicationInitializer() {
//    uploadTmpDir = new File(
//        System.getProperty("java.io.tmpdir")).getAbsolutePath();
//    System.out.println("업로드 임시 폴더: " + uploadTmpDir);
//  }
  
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {AppConfig.class}; // 공통으로 사용할 객체에 대한 정보
  }
  
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {AppWebConfig.class}; // 현재 DispatcherServlet이 처리할 객쳉 대한 정보
  }
  
  @Override
  protected Filter[] getServletFilters() {
    return new Filter[] {new CharacterEncodingFilter("UTF-8") };
  }
  
  @Override
  protected String[] getServletMappings() {
    return new String[] {"/app/*"};
  }
  
  @Override
  protected String getServletName() {
    return "app";
  }
  
//  @Override
//  protected void customizeRegistration(javax.servlet.ServletRegistration.Dynamic registration) {
//    // Servlet 3.0 API의 파일 업로드를 사용하려면 다음과 같이
//    // DispatcherServlet에 설정을 추가해야 한다.
//    // 만약 Spring의 방식으로 파일 업로드를 처리하고 싶다면,
//    // AppConfig.java의 MultipartResolver를 확인하라!
//    //
//    // DispatcherServlet 이 multipart/form-data 으로 전송된 데이터를 처리하려면
//    // 해당 콤포넌트를 등록해야 한다.
//    registration.setMultipartConfig(new MultipartConfigElement(uploadTmpDir, // 업로드 한 파일을 임시 보관할 위치
//        10000000, // 최대 업로드할 수 있는 파일들의 총 크기
//        15000000, // 요청 전체 데이터의 크기
//        2000000 // 업로드 되고 있는 파일을 메모리에 임시 임시 보관하는 크기
//        ));
//  }
}






