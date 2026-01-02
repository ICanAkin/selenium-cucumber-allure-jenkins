@regression
Feature: Products functionality

  Scenario: Products should be listed
    Given user is logged in
    Then products should be visible
