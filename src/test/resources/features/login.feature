Feature: Login feature

  @Login
  Scenario: I want to verify I can login into the application.
    Given a new website launched
    And i open the website in a browser
    When i sign with valid credentials
    Then i should be redirected to account screen
    And  i should see welcome message