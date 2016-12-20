Feature: FamilyTreeTest
  Scenario: Add a person to the family tree
    Given I have <startAmount> people in my family tree
    And I add a person named <name> to the family tree at level <level>
    Then I have <endAmount> people in my family tree
    | startAmount | endAmount | name  | level |
    | 0           | 1         | Marie | 1     |
    | 1           | 2         | Chris | 1     |

  Scenario: Two people are getting married
    Given I have two people, <person1> and <person2> at level <level> in my family tree
    And I let <person1> and <person2> getting married
    Then <person1> and <person2> should be married
    | person1 | person2 | level |
    | Marie   | Chris   | 1     |

  Scenario: Add parent to root node or to an unmarried person
    Given I have a root <person1> at level "1"
    And I add <person2> at level "0"
    And I connect <person2> as parent with <person1>
    Then <person2> is parent of <person1>
    | person1 | person2 |
    | Marie   | Chris   |

  Scenario: Add a child to the family tree
    Given I have <person1> and <person2> in the family tree at level <level>
    And I add <person3> to the family tree at <childrenLevel> which is ≤gender>
    And I connect <person3> as child to <person1> and <person2>
    Then Then <person3> is <child> of <person1> and <person2>
    | person1 | person2 | person3  | level | childrenLevel | gender | child    |
    | Chris   | Marie   | Isabella | 1     | 2             | female | daughter |
    | Chris   | Anna    | Richi    | 1     | 2             | male   | son      |

  Scenario: Two people are siblings
    Given I have <person1> and <person2> in the family tree at level <level>
    And I add <person3> to the family tree at <childrenLevel>
    And I connect <person3> as child to <person1> and <person2>
    And I add <person4> to the family tree at <childrenLevel>
    And I connect <person4> as child to <person1> and <person2>
    Then Then <person3> and <person4> are siblings
      | person1 | person2 | person3  | person4  | level | childrenLevel |
      | Chris   | Marie   | Isabella | Michael  | 1     | 2             |
