package com.mytheresa.steps;

import com.github.webdriverextensions.Bot;
import com.mytheresa.impl.Credentials;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.asserts.SoftAssert;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static com.github.webdriverextensions.Bot.driver;
import static com.mytheresa.Site.site;

@Log
public class ChallengeStepDefinitions {
  String url;
  SoftAssert softAssert;
  List<String> links;
  List<Pair<String, String>> linksStatusCodes;

  @Given("a new website launched")
  public void a_new_website_launched() {
    softAssert = new SoftAssert();
    url = System.getProperty("test.url");
  }

  @Given("i open the website in a browser")
  public void i_open_the_website_in_a_browser() {
    site().landingPage.open(url);
    site().landingPage.waitForPageIsFoullyLoaded(120);
  }

  @When("i fetch each link")
  public void i_fetch_each_link() {
    links = site().landingPage.getLinks();
  }

  @Then("the status code should be 200 or 30x")
  public void the_page_should_return_valid_status_code() {
    links.stream()
        .map(
            l -> {
              Response response;
              RequestSpecification requestSpecification =
                  RestAssured.given()
                      .config(
                          RestAssuredConfig.config()
                              .connectionConfig(
                                  ConnectionConfig.connectionConfig()
                                      .closeIdleConnectionsAfterEachResponse()))
                      .when();
              response =
                  l.endsWith("?")
                      ? requestSpecification.get(l.replaceAll("\\?", ""))
                      : requestSpecification.get(l);
              return Pair.of(l, String.valueOf(response.getStatusCode()));
            })
        .collect(Collectors.toList())
        .forEach(
            links ->
                softAssert.assertTrue(
                    links.getValue().matches("(200|30([0,4]|[7|8]))"),
                    String.format(
                        "<%s> status code should match with [200|30x] -> %s",
                        links.getKey(), links.getValue())));
    softAssert.assertAll();
  }

  @Then("no JavaScript errors should appear in console")
  public void noJavaScriptErrorsShouldAppearInConsole() {
    LogEntries logEntries = driver().manage().logs().get(LogType.BROWSER);
    SoftAssert softAssert = new SoftAssert();
    logEntries.forEach(
        entry -> {
          softAssert.assertFalse(
              entry.getLevel().equals(Level.SEVERE),
              String.format(
                  "Browser has <SEVERE> errors\n%s at %s",
                  entry.getMessage(), new Date(entry.getTimestamp())));
        });
    softAssert.assertAll();
  }

  @When("i sign with {credentialType} credentials")
  public void iSignInWithValidCredentials(Credentials credentials) {
    site().landingPage.openSignInForm();
    site().landingPage.loginForm.submitForm(credentials);
  }

  @Then("i should be redirected to {word} screen")
  public void iShouldBeRedirectedToScreen(String page) {
    site().accountPage.waitForPageIsFoullyLoaded(120);
    Bot.assertCurrentUrlContains(page);
  }

  @And("i should see welcome message")
  public void iShouldSeeWelcomeMessage() {
    Bot.textMatches("HELLO, .*", site().accountPage.dashboard.helloUserMessage);
  }
}
