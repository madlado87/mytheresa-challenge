package com.mytheresa.steps;

import com.google.gson.Gson;
import com.mytheresa.impl.Credentials;
import io.cucumber.java.ParameterType;

import java.io.InputStream;

import static com.utils.FileUtils.readFromInputStream;

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
