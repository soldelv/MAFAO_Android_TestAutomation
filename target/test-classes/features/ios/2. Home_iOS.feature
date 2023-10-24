@regressions
Feature: Home features on Mobile Application | iOS Environment

  Background:
    Given the user accesses to MAFAO iOS application
    When checks if user is already logged in

  Scenario: HOME | Display/Scroll products in Marketplace
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And scrolls down on the home
    Then the user can see the products listed

  Scenario Outline: HOME | Search Products by keyword
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And the user searches for <keyword> on the searchbar
    Then products related to <keyword> are displayed
    Examples:
      |   keyword   |
      |   Sport  |

  Scenario: HOME | Filter products by category
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on Super bonus from Home
    Then products with category Super bonus are displayed
    And taps on New from Home
    Then products with category New are displayed
    And taps on Ends soon from Home
    Then products with category Ends soon are displayed
    And taps on Bonus reached from Home
    Then products with category Bonus reached are displayed
    And taps on Sport from Home
    Then products with category Sport are displayed
    And taps on Supermarket from Home
    Then products with category Supermarket are displayed
    And taps on Equipment from Home
    Then products with category Equipment are displayed
    And taps on Holidays from Home
    Then products with category Holidays are displayed