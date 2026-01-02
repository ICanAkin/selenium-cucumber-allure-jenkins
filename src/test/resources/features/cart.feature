@regression
Feature: Cart functionality

  Scenario: Add product to cart
    Given user is logged in
    When user adds a product to cart
    Then product should be added successfully
