package com.github.pages;

import com.github.components.GithubRows;
import com.github.impl.PullRequest;
import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.WebPage;
import lombok.extern.java.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.webdriverextensions.Bot.currentUrl;
import static com.github.webdriverextensions.Bot.driver;

@Log
public class PullsPage extends WebPage {

  @FindBy(className = "Box-row")
  List<GithubRows> rows;

  @FindBy(css = "[role='navigation'] .current")
  WebElement totalPages;

  @FindBy(className = "next_page")
  public WebElement nextPage;

  @Override
  public void open(Object... arguments) {
    driver().navigate().to(String.format("%s/%s", currentUrl(), "pulls"));
  }

  @Override
  public void assertIsOpen(Object... arguments) throws AssertionError {}

  public void waitForPageIsFoullyLoaded(int seconds) {
    Bot.waitForPageToLoad(seconds);
    rows.forEach(r -> r.waitForComponentIsFoullyLoaded(seconds));
  }

  public Integer getNumberOfPages() {
    return Integer.parseInt(Bot.attributeIn("data-total-pages", totalPages));
  }

  public List<PullRequest> getPullRequest() {
    return rows.stream()
        .map(
            r ->
                PullRequest.builder()
                    .status(Bot.isDisplayed(r.status) ? Bot.textIn(r.status) : "No status")
                    .name(Bot.textIn(r.name))
                    .createdAt(Bot.textIn(r.createdAt).replaceAll(",",""))
                    .author(Bot.textIn(r.author))
                    .comments(
                        Bot.isDisplayed(r.numberOfComments) ? Bot.textIn(r.numberOfComments) : "0")
                    .build())
        .collect(Collectors.toList());
  }
}
