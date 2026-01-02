  @smoke
  Feature: Login functionality

  Scenario: Successful login
    Given user is on login page
    When user logs in with valid credentials
    Then products page should be displayed



