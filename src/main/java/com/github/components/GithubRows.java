package com.github.components;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GithubRows extends WebComponent {
  @FindBy(css = ".d-inline-block a.Link--muted")
  public WebElement status;

  @FindBy(css = ".Link--primary")
  public WebElement name;

  @FindBy(css = ".opened-by relative-time")
  public WebElement createdAt;

  @FindBy(css = ".opened-by .Link--muted")
  public WebElement author;

  @FindBy(css = "[aria-label*='comments'] span")
  public WebElement numberOfComments;

  public void waitForComponentIsFoullyLoaded(int seconds) {
    Bot.waitForElementToDisplay(this.getWrappedWebElement(), seconds);
    //Bot.waitForElementToDisplay(status, seconds);
    Bot.waitForElementToDisplay(name, seconds);
    Bot.waitForElementToDisplay(createdAt, seconds);
    Bot.waitForElementToDisplay(author, seconds);
  }
}
