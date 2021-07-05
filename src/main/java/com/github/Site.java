package com.github;

import com.github.pages.PullsPage;
import com.github.webdriverextensions.WebSite;

import static com.github.webdriverextensions.Bot.driver;

public class Site extends WebSite {
  static final String ENTRY_POINT = "https://github.com";

  public PullsPage pullsPage;

  public static Site site() {
    Site site = new Site();
    site.initElements(driver());
    return site;
  }

  @Override
  public void open(Object... arguments) {
    super.open(String.format("%s/%s", ENTRY_POINT, arguments[0]));
  }

  @Override
  public void assertIsOpen(Object... arguments) throws AssertionError {}
}
