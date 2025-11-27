@Ignore
Feature: courtScheduling API feature 1234
  Test scenarios for courtJudges API deployed to on Swagger Hub

  Scenario: Reading courtScheduling API from Swagger Hub
    Given courtScheduling System is running
    When We make a request to courtScheduling Swagger Hub by id "1234"
    Then We receive a response from Swagger Hub for courtScheduling
