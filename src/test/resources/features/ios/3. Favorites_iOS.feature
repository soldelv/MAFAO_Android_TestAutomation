@regressions
Feature: Favorites features on Mobile Application | iOS Environment

  Background:
    Given the user accesses to MAFAO iOS application
    When checks if user is already logged in

  Scenario: FAVORITES | Add product to Favorites
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on the first product of the marketplace list
    And taps on the heart icon to add the product as a favorite
    And goes to favorite section
    Then the first product of the marketplace list is displayed on Favorites list

  Scenario Outline: FAVORITES | Create and remove an Alert
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And the user searches for <keyword> on the searchbar
    And taps on create an alert
    Then alert created successfully message is displayed
    And alert created button is displayed
    And goes to favorite section
    And taps on Alerts
    Then <keyword> alert is displayed
    And tap on delete alert
    Then alert was successfully deleted
    Examples:
      | keyword |
      |  Sport  |