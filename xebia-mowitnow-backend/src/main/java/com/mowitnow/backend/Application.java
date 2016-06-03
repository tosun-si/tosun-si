package com.mowitnow.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Allows to start spring boot application.
 * 
 * @author Mazlum TOSUN
 */
@SpringBootApplication
public class Application {

  /**
   * Starts spring boot application.
   * 
   * @param args arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
