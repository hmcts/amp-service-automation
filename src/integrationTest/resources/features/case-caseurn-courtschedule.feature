Feature: DEV /case/{case_urn}/courtschedule
  Test scenarios for court schedule by case URN

  Scenario: Reading court schedule by case URN from DEV
    Given System is running
    When We call load court schedule by case_urn "CIK2JQKECS"
    Then We receive CourtScheduleResponse dto
