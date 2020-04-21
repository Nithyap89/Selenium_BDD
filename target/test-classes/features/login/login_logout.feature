@ExtentReport
Feature: As User, I should be able to login with valid credentials

  Background: Login into the application with valid credentials
  	Given I am on the Login page URL "url"
    When Enter username "email"
    And Enter Password "passWord"
    Then click on login button
   

    
   Scenario: Verify the homepage on successful login
   Given Verify the page header "pageHeader"
   When Verify the shipment link
   Then User should be in homescreen
