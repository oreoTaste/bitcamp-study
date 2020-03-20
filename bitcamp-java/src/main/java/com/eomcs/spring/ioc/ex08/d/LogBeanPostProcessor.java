package com.eomcs.spring.ioc.ex08.d;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// 빈 생성 후에 어떤 작업을 수행할 객체를 만들고 싶다면?
// => BeanPostProcessor 규칙에 따라 클래스를 만들라!
public class LogBeanPostProcessor implements BeanPostProcessor {
  public LogBeanPostProcessor() {
    System.out.println("LogBeanPostProcessor()");
  }
  
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    return null;
    //return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }
  
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("===>" + bean.getClass().getName());
    return null;
    //return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }
}
