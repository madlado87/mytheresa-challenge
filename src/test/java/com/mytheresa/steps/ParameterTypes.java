package com.mytheresa.steps;

import com.mytheresa.impl.Credentials;
import io.cucumber.java.ParameterType;

public class ParameterTypes {

  @ParameterType("\\w+")
  public Credentials credentialType(String userType){
    return Credentials.builder()
        .username(
            System.getProperty(
                String.format("%s.user.username.%s", userType, System.getProperty("environment"))))
        .password(
            System.getProperty(
                String.format("%s.user.password.%s", userType, System.getProperty("environment"))))
        .build();
  }
}
