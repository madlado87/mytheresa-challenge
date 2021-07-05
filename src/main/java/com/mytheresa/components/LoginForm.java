package com.mytheresa.components;

import com.github.webdriverextensions.Bot;
import com.github.webdriverextensions.WebComponent;
import com.mytheresa.impl.Credentials;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;

public class LoginForm extends WebComponent {
  @FindBy(id = "email")
  WebElement email;

  @FindBy(id = "pass")
  WebElement pass;

  @FindBy(css = "[type='submit']")
  WebElement submit;

  public void submitForm(Credentials credentials) {
    Bot.waitForElementsToDisplay(Arrays.asList(email, pass, submit));
    Bot.type(credentials.getUsername(), email);
    Bot.type(credentials.getPassword(), pass);
    Bot.click(submit);
  }
}
