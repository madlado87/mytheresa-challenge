Feature: No errors feature

  Scenario: I want to make sure no JavaScript errors when a new website is launched
    Given a new website launched
    When i open the website in a browser
    Then no JavaScript errors should appear in console

  Scenario: I want to check if a page is returning the expected status code.
    Given a new website launched
    And i open the website in a browser
    When i fetch each link
    Then the status code should be 200 or 30x