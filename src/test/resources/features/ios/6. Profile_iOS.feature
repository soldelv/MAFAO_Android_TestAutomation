@regressions
Feature: Profile features on Mobile Application | iOS Environment

  Background:
    Given the user accesses to MAFAO iOS application
    When checks if user is already logged in

  Scenario: PROFILE | View Display Name and Profile Photo
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And user goes to profile screen
    And display name shows correctly
    Then profile picture is displayed correctly

  Scenario: PROFILE | Display bonus wallet
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And user goes to profile screen
    And taps on bonus icon
    Then the transaction history is displayed

  Scenario: PROFILE | Change profile picture by selecting a file from gallery
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And user goes to profile screen
    And taps on profile picture
    And tap on Yes, take a photo button
    And tap on Photo Gallery option
    And selects a photo from gallery
    And tap on change avatar to confirm
    Then profile picture is updated correctly

  Scenario: PROFILE | Notifications
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And user goes to profile screen
    And taps on notifications
    Then all the notifications received are displayed
    And goes to home section
    And taps on notification icon
    Then all the notifications received are displayed

  Scenario: PROFILE | My Shop (Seller’s Dashboard, Odoo view)
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And user goes to profile screen
    And clicks on My Shop option
    Then seller dashboard from Odoo view is displayed successfully

  Scenario: PROFILE | Settings | Account Settings | Edit Profile
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And user goes to profile screen
    And taps on settings
    And taps on profile option
    And changes the display name
    Then the display name was successfully changed

  Scenario: PROFILE | Settings | Support | Backup Account
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And user goes to profile screen
    And taps on settings
    And taps on backup account option
    And taps backup button to confirm the backup
    Then backup success message is displayed

  #Scenario: PROFILE | Settings | General Settings | Location
  #  Given enters a valid secret code and taps on confirm
  #  When the user is logged in on MAFAO mobile application
  #  And user goes to profile screen
  #  And taps on settings
  #  And taps on location
  #  And the location is not activated
  #  And allows Mafao to use location
  #  Then your location has been activated message is displayed

  Scenario: PROFILE | Settings | Account Settings | Change Secret Code
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And user goes to profile screen
    And taps on settings
    And taps on Secret code to change it
    And enters the current secret code
    And taps on Change my secret code
    And enters the current secret code
    And enters the new secret code twice
    Then the pincode was successfully changed
    And logins again with the new secret code
    Then the user is logged in on MAFAO mobile application

  Scenario: PROFILE | Reset pincode to default
    Given logins again with the new secret code
    When the user is logged in on MAFAO mobile application
    And user goes to profile screen
    And reset pincode to default secret code
    Then the user is logged in on MAFAO mobile application

  Scenario: PROFILE | Log out from iOS
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And user goes to profile screen
    And clicks on log out
    Then the user is logged out successfully