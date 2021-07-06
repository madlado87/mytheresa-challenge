package com.mytheresa.steps;

import com.github.webdriverextensions.WebDriverExtensionsContext;
import com.utils.RemoteWebDriverUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

  @Before()
  public void beforeScenario() throws Exception {
    RemoteWebDriverUtils remoteWebDriverUtils = new RemoteWebDriverUtils();
    WebDriverExtensionsContext.setDriver(remoteWebDriverUtils.instanceDriver());
  }

  @After()
  public void afterScenario() {
    WebDriverExtensionsContext.getDriver().close();
    WebDriverExtensionsContext.getDriver().quit();
    WebDriverExtensionsContext.removeDriver();
  }
}
