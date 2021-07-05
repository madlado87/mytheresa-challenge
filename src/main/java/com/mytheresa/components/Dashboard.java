package com.mytheresa.components;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard extends WebComponent {
  @FindBy(className = "hello")
  public WebElement helloUserMessage;


  public void waitForComponentIsFoullyLoaded(int seconds) {
    Bot.waitForElementToDisplay(this.getWrappedWebElement(), seconds);
    Bot.waitForElementToDisplay(helloUserMessage, seconds);
  }
}
