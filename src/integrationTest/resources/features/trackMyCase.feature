Feature: Track My Case UI
  Test scenarios for Track My Case UI on DEV environment

  Scenario: User logs in using OneLogin
    Given Track My Case UI is running
    When User clicks button "Start now"
    And Wait "5" seconds

  Scenario: Access Track My Case dashboard
    Given I navigate to Track My Case UI
    And Im on "Track my case - Home" page
    And Wait "3" seconds
    And I click on "Start now" button
    And Wait "3" seconds
    And Move to a new tab
    And Wait "2" seconds
    And I click on "Sign in" button
    And I enter email
    And I click on "Continue" button
    And I enter password
    And I click on "Continue" button
    And I enter 2FA
    And I click on "Continue" button
    And Stop

