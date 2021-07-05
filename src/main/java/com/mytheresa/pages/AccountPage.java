package com.mytheresa.pages;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.WebPage;
import com.mytheresa.components.Dashboard;
import lombok.extern.java.Log;
import org.openqa.selenium.support.FindBy;

@Log
public class AccountPage extends WebPage {

  @FindBy(className = "dashboard")
  public Dashboard dashboard;

  @Override
  public void open(Object... arguments) {
    String url = String.format("/%s/pulls", arguments[0]);
    super.open(url);
    log.info(String.format("URL \"%s\" was opened", url));
  }

  @Override
  public void assertIsOpen(Object... arguments) throws AssertionError {}

  public void waitForPageIsFoullyLoaded(int seconds) {
    Bot.waitForPageToLoad(seconds);
    dashboard.waitForComponentIsFoullyLoaded(seconds);
  }
}
