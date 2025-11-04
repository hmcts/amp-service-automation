Feature: Track My Case UI
  Test scenarios for Track My Case UI on DEV environment

  Scenario: User logs in using OneLogin
    Given Track My Case UI is running
    When User clicks button "Start now"
    And Wait "5" seconds

  Scenario: Access DPS homepage
    Given I navigate to DPS service
    And Im on "Track my case - Home" page
    And Wait "3" seconds
    And I click on "Start now" button
    And Wait "3" seconds
    And I click on "Sign in" button
    And I enter email
    And I click on "Continue" button
    And I enter password
    And I click on "Continue" button
    And I enter 2FA
    And I click on "Continue" button
    And Stop
#    And CLick start
#    and enter user login
#    and enter user password
#    and enter 2fa
