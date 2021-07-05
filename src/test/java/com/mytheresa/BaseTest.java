package com.mytheresa;

import com.github.webdriverextensions.WebDriverExtensionsContext;
import com.utils.RemoteWebDriverUtils;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static com.utils.PropertyUtils.loadPropertiesFile;

public class BaseTest extends AbstractTestNGCucumberTests {
  private static final String PROPERTY_FILE_NAME = "test.properties";
  RemoteWebDriverUtils remoteWebDriverUtils;

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

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() throws Exception {
    remoteWebDriverUtils = new RemoteWebDriverUtils();
    WebDriverExtensionsContext.setDriver(remoteWebDriverUtils.instanceDriver());
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod() {
    WebDriverExtensionsContext.getDriver().close();
    WebDriverExtensionsContext.getDriver().quit();
    WebDriverExtensionsContext.removeDriver();
  }
}
