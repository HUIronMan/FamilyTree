Feature: FamilyTreeTest
  Scenario: Add a person to the family tree
    Given I have "0" people in my family tree
    And I add a person to the family tree
    Then I have "1" person in my family tree
