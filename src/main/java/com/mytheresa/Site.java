package com.mytheresa;

import com.github.webdriverextensions.WebSite;
import com.mytheresa.pages.AccountPage;
import com.mytheresa.pages.LandingPage;

import static com.github.webdriverextensions.Bot.driver;

// 5_@XVEcJpJT?ReT
public class Site extends WebSite {
  public LandingPage landingPage;
  public AccountPage accountPage;

  public static Site site() {
    Site site = new Site();
    site.initElements(driver());
    return site;
  }

  @Override
  public void open(Object... arguments) {
    site().landingPage.open();
  }

  @Override
  public void assertIsOpen(Object... arguments) throws AssertionError {}
}
