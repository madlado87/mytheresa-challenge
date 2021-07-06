package com.mytheresa;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static com.utils.PropertyUtils.loadPropertiesFile;

public class BaseTest extends AbstractTestNGCucumberTests {
  private static final String PROPERTY_FILE_NAME = "test.properties";


  @BeforeSuite(alwaysRun = true)
  public void setUp() throws IOException {
    Properties prop = new Properties();
    try (InputStream resourceAsStream = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME))) {
      prop.load(resourceAsStream);
    } catch (IOException e) {
      System.err.println("Unable to load properties file : " + PROPERTY_FILE_NAME);
    }
    loadPropertiesFile(prop);
  }


}
