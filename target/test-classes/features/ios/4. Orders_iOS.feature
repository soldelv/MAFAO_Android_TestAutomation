@regressions
Feature: Orders features on Mobile Application | iOS Environment

  Background:
    Given the user accesses to MAFAO iOS application
    When checks if user is already logged in

  Scenario: ORDERS | Change pick up location before check out
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on a product from the marketplace - iOS
    And taps on add to cart button
    And goes to orders screen
    And on Product pick-up location taps on change button
    And selects a pick up location
    And tap on register button to confirm the operation
    Then pick up location was successfully changed

  Scenario: ORDERS | Add and remove a product from cart
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on a product from the marketplace - iOS
    And taps on plus icon
    And taps on minus icon
    And taps on add to cart button
    And goes to orders screen
    Then the product selected is displayed on the order page
    And taps on trash icon to remove product from the list
    Then the product was removed successfully

  Scenario: ORDERS | Check out successfully by Paypal
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on a product from the marketplace - iOS
    And taps on add to cart button
    And goes to orders screen
    And taps on change payment method
    And selects Paypal as a payment method
    And taps pay your order
    And the user logs into Paypal and confirm the payment
    Then your order has been registered screen should be displayed

  Scenario: ORDERS | Check out successfully by MAFAO wallet
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on the product with lowest price from marketplace
    And tap add to cart for the product with the lowest price
    And goes to orders screen
    And taps on change payment method
    And selects Mafao as a payment method
    And taps pay your order to finish the purchase
    Then your order has been registered screen should be displayed

  Scenario: ORDERS | Check out successfully by Credit card
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on the product with lowest price from marketplace
    And tap add to cart for the product with the lowest price
    And goes to orders screen
    And taps pay your order to finish the purchase
    Then your order has been registered screen should be displayed

  Scenario: ORDERS | Last order: Paid Order tab display after checkout & Transaction Details
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And goes to orders screen
    And user goes to Pay Order tab
    Then the last order is displayed on the list
    And taps on the last order
    Then paid order transaction details are displayed

  Scenario: WITHDRAWAL | Product list to Pick Up
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And goes to Withdrawal section
    Then a product with Ready to Pick Up status is displayed

  Scenario: ORDERS | Make a review from Paid orders tab
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And goes to orders screen
    And user goes to Pay Order tab
    And scrolls down to see orders with status delivered
    And taps on Give your opinion
    And adds a review on the product
    Then the review is successfully submitted

  Scenario: ORDERS | Make a review from Paid Order Transaction Details
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And goes to orders screen
    And user goes to Pay Order tab
    And searches for a order with status delivered
    And taps on review button from order detail
    And adds a review on the product
    Then the review is successfully submitted

