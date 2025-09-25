@Ignore
Feature: courtJudges API feature 1234
  Test scenarios for courtJudges API deployed to on Swagger Hub

  Scenario: Reading courtJudges API from Swagger Hub
    Given courtJudges System is running
    When We make a request to courtJudges Swagger Hub by id "1234"
    Then We receive a response from Swagger Hub for courtJudges
