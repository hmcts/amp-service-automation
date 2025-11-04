Feature: APIM DEV integration
  Test scenarios for court schedule by case URN

  Scenario: Reading court schedule by case URN from DEV for 28DI1953715
    Given APIM System is running
    When We load court sitting details from APIM by case_urn "28DI1953715"
    Then We receive CourtScheduleResponse dto from APIM

