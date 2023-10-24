@test-set:regression
Feature: Favorites features in MAFAO APP Mobile | Android

  Background:
    Given the user accesses to MAFAO Android application
    When the user enters valid credentials - Android
    Then the user is logged in on MAFAO mobile application - Android

  Scenario Outline: Add products as Favorite from marketplace list
    Given the user is on marketplace screen - Android
    When chooses <product_1>, <product_2> from marketplace and taps as favorite - Android
    And goes to favorite section - Android
    Then <product_1>, <product_2> are displayed on Favorites list - Android
    Examples:
      |   product_1   |   product_2  |
      | Football Ball | Baby clothes |

  Scenario Outline: Add products as Favorite from product screen
    Given the user is on marketplace screen - Android
    When scrolls down searching for <product_1> - Android
    And taps on <product_1> from marketplace - Android
    And taps on the heart icon to add the product as a favorite - Android
    And goes to favorite section - Android
    Then <product_1> is displayed on Favorites list - Android
    Examples:
      |   product_1     |
      | 3 in 1 Multipod |

  Scenario: Remove products from Favorite list
    Given the user is on marketplace screen - Android
    When the user has products on favorites list - Android
    And taps on the heart icon to remove the product as a favorite - Android
    Then the products are not longer displayed on favorites list - Android
