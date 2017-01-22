Feature: Planification

  As a local operator, I want to enter a context, in order to get a planication.

  Background:
    Given an operator who enter a context
    When a planification is created

  Scenario: U.S 18 : Use a single drone
    Then the planification use a single drone

  Scenario: U.S 19 : load one item at a time
    Then the drone of the planification load one item at a time

  Scenario: U.S 20 : maximize load
    Then the drone of the planification maximize the load of each drone

  Scenario: U.S 21 : use the whole fleet
    Then the planification use the whole fleet


