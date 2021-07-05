package com.mytheresa.pages;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.WebPage;
import com.mytheresa.components.LoginForm;
import com.utils.SeleniumUtils;
import lombok.extern.java.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.github.webdriverextensions.Bot.driver;

@Log
public class LandingPage extends WebPage {

  @FindBy(css = "[href]")
  List<WebElement> links;

  @FindBy(xpath = ".//div[@class='account-login']/ancestor::li")
  public LoginForm loginForm;

  @Override
  public void open(Object... arguments) {}

  @Override
  public void assertIsOpen(Object... arguments) throws AssertionError {}

  /***
   *
   */
  public void waitForPageIsFoullyLoaded(int seconds) {
    Bot.waitForPageToLoad(seconds);
  }

  public List<String> getLinks() {
    log.info(
        String.format("Landing page (%s) contains <%d> links", Bot.currentUrl(), links.size()));
    return SeleniumUtils.getPageLinks(links);
  }

  public void openSignInForm() {
    Actions actions = new Actions(driver());
    actions.moveToElement(loginForm.getWrappedWebElement()).build().perform();
  }
}
