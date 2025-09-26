@Ignore
Feature: Track My Case UI
  Test scenarios for Track My Case UI on DEV environment

  Scenario: User logs in using OneLogin
    Given Track My Case UI is running
    When User clicks button "Start now"

  Scenario: Access DPS homepage
    Given I navigate to DPS service
    And Im on "Track My Case - Home" page
#    And CLick start
#    and enter user login
#    and enter user password
#    and enter 2fa
