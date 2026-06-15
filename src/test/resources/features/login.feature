@login
Feature: Login
  As a Swag Labs shopper
  I want to authenticate in the mobile app
  So that I can access the product catalog

  Background:
    Given the user is on the login screen

  @smoke
  Scenario: Successful login with valid credentials
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the products screen is displayed

  @negative
  Scenario: Login blocked for a locked out user
    When the user submits username "locked_out_user" and password "secret_sauce"
    Then a login error message "Sorry, this user has been locked out." is shown

  @negative
  Scenario Outline: Login fails with invalid credentials
    When the user submits username "<username>" and password "<password>"
    Then a login error message "<message>" is shown

    Examples:
      | username       | password       | message                                                                   |
      | standard_user  | wrong_password | Username and password do not match any user in this service.              |
      | unknown_user   | secret_sauce   | Username and password do not match any user in this service.              |
