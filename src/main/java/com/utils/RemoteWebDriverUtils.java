package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.NonNull;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteWebDriverUtils {
  DesiredCapabilities capabilities;

  public WebDriver instanceDriver() throws MalformedURLException {
    capabilities = new DesiredCapabilities();
    return "local".equalsIgnoreCase(System.getProperty("test.execution"))
        ? instanceLocalDriver(
            System.getProperty("test.browser") == null
                ? "chrome"
                : System.getProperty("test.browser"))
        : instanceRemoteDriver(
            System.getProperty("seleniumGrid"),
            System.getProperty("test.browser") == null
                ? "chrome"
                : System.getProperty("test.browser"));
  }

  public WebDriver instanceLocalDriver(@NonNull String browserName) {
    WebDriver driver;
    if ("chrome".equalsIgnoreCase(browserName)) {
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.merge(instanceDriverCapabilities());
      driver = new ChromeDriver(options);
    } else if ("firefox".equals(browserName)) {
      WebDriverManager.firefoxdriver().setup();
      FirefoxOptions options = new FirefoxOptions();
      options.merge(instanceDriverCapabilities());
      driver = new FirefoxDriver(options);
    } else {
      throw new WebDriverException(
          String.format(
              "%s is not implemented please use Chrome or Firefox as browser for tests execution",
              browserName));
    }
    return driver;
  }

  public WebDriver instanceRemoteDriver(@NonNull String remoteAddress, @NonNull String browserName)
      throws MalformedURLException {
    capabilities.setBrowserName(browserName);
    capabilities.merge(instanceDriverCapabilities());
    return new RemoteWebDriver(
        new URL(String.format("http://%s/wd/hub", remoteAddress)), capabilities);
  }

  public DesiredCapabilities instanceDriverCapabilities() {
    capabilities.setAcceptInsecureCerts(true);
    capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, PageLoadStrategy.NORMAL);
    capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
    capabilities.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
    return capabilities;
  }

  public void mergeCapabilities(DesiredCapabilities capabilities) {
    this.capabilities.merge(capabilities);
  }
}
