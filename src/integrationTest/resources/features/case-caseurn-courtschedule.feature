Feature: DEV /case/{case_urn}/courtschedule
  Test scenarios for court schedule by case URN

  Scenario: Reading court schedule by case URN from DEV for CIK2JQKECS
    Given System is running
    When We call load court schedule by case_urn "CIK2JQKECS"
    Then We receive CourtScheduleResponse dto

  Scenario: Reading court schedule by case URN from DEV for CCZFJL1QK4
    Given System is running
    When We call load court schedule by case_urn "CCZFJL1QK4"
    Then We receive CourtScheduleResponse dto
