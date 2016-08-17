package com.epam;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class TestLambdaLogger implements LambdaLogger{
  private Logger logger;
  
  public TestLambdaLogger(Class<?> classdesc){
    this.logger = Logger.getLogger(classdesc.getName());
  }
  
  public void log(String string) {
    System.out.println("AWS LAMBDA LOG * :"+ string);
  }
}
