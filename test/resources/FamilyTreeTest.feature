Feature: FamilyTreeTest
  Scenario Outline: Add a person to the family tree
    Given I have <startAmount> people in my family tree
    And I add a person named <name> to the family tree at level <level>
    Then I have <endAmount> people in my family tree
    Examples:
      | startAmount | endAmount | name  | level |
      | 0           | 1         | Marie | 1     |
      | 1           | 2         | Chris | 1     |

  Scenario Outline: Two people are getting married
    Given I add a person named <person1> to the family tree at level <level>
    And I add a person named <person2> to the family tree at level <level>
    And I let <person1> and <person2> getting married
    Then <person1> and <person2> should be married
    Examples:
      | person1 | person2 | level |
      | Marie   | Chris   | 1     |

  Scenario Outline: Add parent to root node or to an unmarried person
    Given I add a person named <person1> to the family tree at level 1
    And I add a person named <person1> to the family tree at level 0
    And I connect <person1> as a child to <person2>
    Then <person2> is parent of <person1>
    Examples:
      | person1 | person2 |
      | Marie   | Chris   |

  Scenario Outline: Add a child to the family tree
    Given I add a person named <person1> to the family tree at level <level>
    And I add a person named <person2> to the family tree at level <level>
    And I add a person named <person3> to the family tree at level <childrenLevel> who is <gender>
    And I connect <person3> as child to <person1> and <person2>
    Then Then <person3> is <child> of <person1> and <person2>
    Examples:
      | person1 | person2 | person3  | level | childrenLevel | gender | child    |
      | Chris   | Marie   | Isabella | 1     | 2             | female | daughter |
      | Chris   | Anna    | Richi    | 1     | 2             | male   | son      |

  Scenario Outline: Two people are siblings
    Given I add a person named <person1> to the family tree at level <level>
    And I add a person named <person2> to the family tree at level <level>
    And I add a person named <person3> to the family tree at level <childrenLevel>
    And I connect <person3> as child to <person1> and <person2>
    And I add a person named <person4> to the family tree at level <childrenLevel>
    And I connect <person4> as child to <person1> and <person2>
    Then Then <person3> and <person4> are siblings
    Examples:
      | person1 | person2 | person3  | person4  | level | childrenLevel |
      | Chris   | Marie   | Isabella | Michael  | 1     | 2             |

  Scenario Outline: Two people are cousins
    Given I add a person named <person1> to the family tree at level <level>
    And I add a person named <person2> to the family tree at level <level>
    And I add a person named <person3> to the family tree at level <childrenLevel>
    And I connect <person3> as child to <person1> and <person2>
    And I add a person named <person4> to the family tree at level <childrenLevel>
    And I connect <person4> as child to <person1> and <person2>
    And I add a person named <person5> to the family tree at level <grandChildLevel>
    And I connect <person5> as child to <person3>
    And I add a person named <person6> to the family tree at level <grandChildLevel>
    And I connect <person6> as child to <person4>
    Then <person5> is cousin of <person6>
    Examples:
    | person1 | person2 | person3  | person4  | person5 | person6  | level | childrenLevel | grandChildLevel |
    | Chris   | Marie   | Isabella | Michael  | Jon     | Anabelle | 1     | 2             | 3               |
