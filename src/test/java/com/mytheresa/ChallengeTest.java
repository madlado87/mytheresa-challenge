package com.mytheresa;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.java.Log;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;

import java.util.logging.Level;

@Log
@CucumberOptions(
    features = {
      "src/test/resources/features/login.feature",
      "src/test/resources/features/no_errors.feature"
    },
    glue = {"com.mytheresa.steps"},
    tags = "@Login")
public class ChallengeTest extends BaseTest {

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
