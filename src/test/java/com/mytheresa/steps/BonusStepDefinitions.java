package com.mytheresa.steps;

import com.github.Site;
import com.github.impl.PullRequest;
import com.github.webdriverextensions.Bot;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.Site.site;
import static com.utils.ClassUtils.getDeclaredFields;
import static com.utils.FileUtils.generateCsvFile;

public class BonusStepDefinitions {

  @Given("a repository {string}")
  public void aRepository(String repoName) {
    Site site = new Site();
    site.open((Object) repoName);
  }

  @When("i navigate to {word} screen")
  public void iNavigateToPullRequestScreen(String screen) {
    switch (screen) {
      case "pulls":
        site().pullsPage.open();
        site().pullsPage.waitForPageIsFoullyLoaded(120);
        break;
      default:
        throw new WebDriverException(
            String.format("Navigation to <%s> screen is not implemented yet", screen));
    }
  }

  @Then("i want to export pull request information in {string} file")
  public void iWantToExportPullRequestInformationInFile(String csvFileName) throws IOException {
    int numberOfPages = site().pullsPage.getNumberOfPages();
    List<PullRequest> prList = new ArrayList<>();
    for (int i = 0; i <= numberOfPages; i++) {
      prList.addAll(site().pullsPage.getPullRequest());
      Bot.click(site().pullsPage.nextPage);
      Bot.waitFor(3);
    }
    generateCsvFile(csvFileName, prList, getDeclaredFields(PullRequest.class));
  }
}
