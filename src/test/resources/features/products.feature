@products
Feature: Shopping cart
  As an authenticated shopper
  I want to add products to my cart
  So that I can review them before checkout

  @smoke
  Scenario: Add a single product to the cart
    Given the user is logged in as "standard_user" with password "secret_sauce"
    When the user adds 1 product to the cart
    Then the cart badge shows 1

  @regression
  Scenario: Add multiple products to the cart
    Given the user is logged in as "standard_user" with password "secret_sauce"
    When the user adds 3 products to the cart
    Then the cart badge shows 3
