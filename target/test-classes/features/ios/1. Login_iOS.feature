@regressions
Feature: Login features on Mobile Application | iOS Environment

  Background:
    Given the user accesses to MAFAO iOS application
    When checks if user is already logged in

  Scenario: LOGIN | Successful login via pincode
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    Then the user should be logged in successfully

  Scenario: LOGIN | Incorrect pincode
    And enters a invalid secret code and taps on confirm
    Then invalid pincode entered error message must be displayed

  #Scenario: LOGIN | Reset pincode after a fail login
  #  Given taps on forgot pincode
  #  When selects a country - iOS
  #  And enters a registered mobile number and taps on send code
  #  And enters the verification code and taps on send
  #  And enters the new secret code twice
  #  And the pincode was successfully changed
  #  And logins again with the new secret code
  #  Then the user is logged in on MAFAO mobile application