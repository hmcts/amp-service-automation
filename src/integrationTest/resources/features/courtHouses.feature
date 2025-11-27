@Ignore
Feature: courtHouses API feature 1234
  Test scenarios for courtHouses API deployed to on Swagger Hub

  Scenario: Reading courtHouses API from Swagger Hub
    Given courtHouses System is running
    When We make a request to courtHouses Swagger Hub by id "1234"
    Then We receive a response from Swagger Hub for courtHouses
