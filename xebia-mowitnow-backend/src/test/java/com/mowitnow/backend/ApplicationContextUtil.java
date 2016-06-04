package com.mowitnow.backend;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware {

  private static ApplicationContext ctx;

  @Override
  public void setApplicationContext(ApplicationContext appContext) throws BeansException {
    ctx = appContext;

  }

  public static ApplicationContext getApplicationContext() {
    return ctx;
  }
}
