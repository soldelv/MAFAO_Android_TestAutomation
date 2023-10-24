@regressions
Feature: Product features on Mobile Application | iOS Environment

  Background:
    Given the user accesses to MAFAO iOS application
    When checks if user is already logged in

  Scenario: PRODUCT PAGE | Product detailed page viewing
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on a product from the marketplace - iOS
    Then product information are displayed correctly

  Scenario: PRODUCT PAGE | Seller icon screen (Auchan logo)
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on a product from the marketplace - iOS
    And taps on Auchan logo to see seller info
    Then seller info is displayed correctly

  Scenario: PRODUCT PAGE | Report this product
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on a product from the marketplace - iOS
    And taps on Report this product
    And fills the report the product form
    And report to validate message are displayed
    Then taps on Ok to send the report

  Scenario: PRODUCT PAGE | Similar Products list
    Given enters a valid secret code and taps on confirm
    When the user is logged in on MAFAO mobile application
    And the user is on marketplace screen
    And taps on the first product of the marketplace list
    And goes to similar products section
    Then similar products for first product of the list are displayed