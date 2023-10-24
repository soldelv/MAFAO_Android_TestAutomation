@test-set:regression
Feature: Home features on Mobile Application | Android Environment

  Background:
    Given the user accesses to MAFAO Android application
    When the user enters valid credentials - Android
    Then the user is logged in on MAFAO mobile application - Android

  Scenario: Display products in Marketplace
    Given the user is on marketplace screen - Android
    When scrolls down on the home - Android
    Then the user can see the products listed - Android

  Scenario Outline: Search products by keyword
    Given the user is on marketplace screen - Android
    When the user searches for <keyword> on the searchbar - Android
    Then products related with <keyword> are displayed - Android
    Examples:
      |   keyword   |
      |   football  |

  @Ignore
  Scenario Outline: Filter products by category
    Given the user is on marketplace screen - Android
    When taps on <category> from Home - Android
    Then products related to <category> are displayed - Android
    Examples:
      |   category   |
      |   Sport      |