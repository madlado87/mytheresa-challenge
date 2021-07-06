Feature: export repository information into a csv file.
  
  Scenario: export open pull request
    Given a repository "appwrite/appwrite"
    When i navigate to pulls screen
    Then i want to export pull request information in "open_pull_request.csv" file