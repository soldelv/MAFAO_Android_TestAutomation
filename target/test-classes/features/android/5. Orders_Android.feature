git @test-set:regression
Feature: Orders features on Mobile Application | Android Environment

  Background:
    Given the user accesses to MAFAO Android application
    When the user enters valid credentials - Android
    Then the user is logged in on MAFAO mobile application - Android

  Scenario Outline: Add and remove a product from cart
    Given the user is on marketplace screen - Android
    When taps on <product_1> from marketplace - Android
    And taps on plus icon twice - Android
    And taps on minus icon once - Android
    And taps to add to cart icon - Android
    And goes to orders section - Android
    Then <product_1> is displayed on Orders list - Android
    And taps on trash icon to remove product from the list - Android
    Then <product_1> is not longer displayed on the list - Android
    Examples:
      | product_1    |
      | Baby clothes |


  Scenario Outline: Purchase successfully by Paypal
    Given the user is on marketplace screen - Android
    When taps on <product_1> from marketplace - Android
    And taps to add to cart icon - Android
    And goes to orders section - Android
    And taps pay your order - Android
    And the user logs into Paypal and confirm the payment - Android
    Then your order has been registered screen should be displayed - Android
    Examples:
      |  product_1     |
      | Football Ball  |

