package com.mytheresa.steps;

import com.mytheresa.impl.Credentials;
import io.cucumber.java.ParameterType;

public class ParameterTypes {

  @ParameterType("\\w+")
  public Credentials credentialType(String userType){
    return Credentials.builder()
        .username(
            System.getProperty(
                String.format("test.%s.user.username", userType)))
        .password(
            System.getProperty(
                String.format("test.%s.user.password", userType)))
        .build();
  }
}
