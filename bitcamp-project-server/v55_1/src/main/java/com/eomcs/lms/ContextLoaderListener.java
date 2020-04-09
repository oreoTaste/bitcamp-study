package com.eomcs.lms;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@WebListener // 이 애노테이션을 붙이면 서블릿 컨테이너가 이 객체를 관리한다.
public class ContextLoaderListener implements ServletContextListener {

  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {

    try {
      ApplicationContext iocContainer = new AnnotationConfigApplicationContext(//
          AppConfig.class);
      printBeans(iocContainer);

      ServletContext servletContext = sce.getServletContext();
      servletContext.setAttribute("iocContainer", iocContainer);

      logger.debug("======================================================");

    } catch(Exception e) {

    }
  }


  private void printBeans(ApplicationContext appCtx) {
    logger.debug("Spring IoC 컨테이너에 들어있는 객체들");
    String[] beanNames = appCtx.getBeanDefinitionNames();
    for(String beanName : beanNames) {
      logger.debug(String.format("%s ===>\n\t\t %s", beanName, appCtx.getBean(beanName).getClass().getName()));
    }
  }


  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }



}
