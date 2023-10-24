@test-set:regression
Feature: Login features on Mobile Application | Android Environment

  Background:
    Given the user accesses to MAFAO Android application

  Scenario: Successful login with valid credentials
    When the user taps on Log in button - Android
    Then the user is on the login screen - Android
    And selects a country - Android
    And enters a valid mobile number and taps on send code - Android
    And enters the verification code and taps on send - Android
    And enters a valid secret code and taps on confirm - Android
    Then the user should be logged in successfully - Android

  Scenario Outline: Invalid login with incorrect secret code
    When the user taps on Log in button - Android
    Then the user is on the login screen - Android
    And selects a country - Android
    And enters a valid mobile number and taps on send code - Android
    And enters the verification code and taps on send - Android
    And enters incorrect <secret_code> and taps on confirm - Android
    Then the user should see an error message - Android
    Examples:
      | secret_code |
      |    111111   |

  Scenario Outline: Reset pincode
    When the user taps on Log in button - Android
    Then the user is on the login screen - Android
    And selects a country - Android
    And enters a valid mobile number and taps on send code - Android
    And enters the verification code and taps on send - Android
    And taps on forgot secret code - Android
    And selects a country - Android
    And enters a valid mobile number and taps on send code - Android
    And enters the verification code and taps on send - Android
    And enters the new <secret_code> code twice - Android
    Then the pincode is reset successfully - Android
    Examples:
      | secret_code |
      |    200723   |