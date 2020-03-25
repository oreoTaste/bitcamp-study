package com.eomcs.lms;

import java.lang.reflect.Method;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.util.RequestHandler;
import com.eomcs.util.RequestMapping;
import com.eomcs.util.RequestMappingHandlerMapping;

public class ContextLoaderListener implements ApplicationContextListener {
  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
//      beans.put("sqlSessionFactory", sqlSessionFactory);
//      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
//      beans.put("transactionManager", txManager);
      
      // Spring IoC 컨테이너 준비
      ApplicationContext appCtx = new AnnotationConfigApplicationContext(
          AppConfig.class
//          DatabaseConfig.class,
//          MybatisConfig.class
          );
      
      printBeans(appCtx);
      
      context.put("iocContainer", appCtx);
//      appCtx.printBeans();
      
      logger.debug("======================================================");
      RequestMappingHandlerMapping handlerMapper = new RequestMappingHandlerMapping();
      String[] beanNames = appCtx.getBeanNamesForAnnotation(Component.class);
      for(String beanName : beanNames) {
        Object component = appCtx.getBean(beanName);
        Method method = getRequestHandler(component.getClass());
        
        if(method != null) {
          RequestHandler requestHandler = new RequestHandler(method, component);
          handlerMapper.addHandler(requestHandler.getPath(), requestHandler);
        }
      }
      
      //ServerApp에서 RequestHandler를 사용할 수 있도록 공유한다.
      context.put("handlerMapper", handlerMapper);
      
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


  private Method getRequestHandler(Class<?> type) {
    Method[] methods = type.getMethods();
    for(Method method : methods) {
      RequestMapping anno = method.getAnnotation(RequestMapping.class);
      if(anno != null)
        return method;
    }
    return null;
  }


  @Override
  public void contextDestroyed(Map<String, Object> context) {
  }



}
