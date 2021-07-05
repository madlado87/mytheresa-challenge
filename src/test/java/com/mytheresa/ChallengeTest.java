package com.mytheresa;

import io.cucumber.testng.CucumberOptions;
import lombok.extern.java.Log;
import org.testng.annotations.DataProvider;

@Log
@CucumberOptions(
    features = {
      "src/test/resources/features/login.feature",
      "src/test/resources/features/no_errors.feature"
    },
    glue = {"com.mytheresa.steps"},
    plugin = {"pretty"})
public class ChallengeTest extends BaseTest {

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
