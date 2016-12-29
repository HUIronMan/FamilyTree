Feature: FamilyTreeTest
  Scenario Outline: Add a person to the family tree
    Given I add a person named <name> to the family tree at level <level> who is <gender>
    Then <name> is in my family tree
    Examples:
      | name  | level | gender |
      | Marie | 1     | female |
      | Chris | 1     | male   |

  Scenario Outline: Two people are getting married
    Given I add a person named <person1> to the family tree at level <level> who is <gender1>
    And I add a person named <person2> to the family tree at level <level> who is <gender2>
    And I let <person1> and <person2> getting married
    Then <person1> is married to <person2>
    Examples:
      | person1   | person2 | level | gender1 | gender2 |
      | Marie     | Chris   | 1     | female  | male    |
      | Chris     | Marie    | 1     | male    | female  |

  Scenario Outline: Add parent to root node or to an unmarried person
    Given I add a person named <person1> to the family tree at level 1 who is <female>
    And I add a person named <person2> to the family tree at level 0 who is <male>
    And I connect <person1> as a child to <person2>
    Then <person1> is child of <person2>
    Examples:
      | person1 | person2 | female | male |
      | Marie   | Chris   | female | male |

  Scenario Outline: Add a child to the family tree
    Given I add a person named <person1> to the family tree at level <level> who is <male>
    And I add a person named <person2> to the family tree at level <level> who is <female>
    And I add a person named <person3> to the family tree at level <childLevel> who is <childGender>
    And I connect <person3> as a child to <person1>
    And I connect <person3> as a child to <person2>
    Then <person3> is child of <person1>
    And <person3> is child of <person2>
    Examples:
      | person1 | person2 | male | female | person3  | level | childLevel | childGender | child    |
      | Chris   | Marie   | male | female | Isabella | 1     | 2          | female      | daughter |
      | Chris   | Anna    | male | female | Richi    | 1     | 2          | male        | son      |

  Scenario Outline: Two people are siblings
    Given I add a person named <person1> to the family tree at level <level> who is <male>
    And I add a person named <person2> to the family tree at level <level> who is <female>
    And I add a person named <person3> to the family tree at level <childrenLevel> who is <female>
    And I connect <person3> as a child to <person1>
    And I connect <person3> as a child to <person2>
    And I add a person named <person4> to the family tree at level <childrenLevel> who is <male>
    And I connect <person4> as a child to <person1>
    And I connect <person4> as a child to <person2>
    Then <person3> is sibling of <person4>
    Examples:
      | person1 | person2 | person3  | person4  | male | female | level | childrenLevel |
      | Chris   | Marie   | Isabella | Michael  | male | female | 1     | 2             |

  Scenario Outline: Two people are cousins
    Given I add a person named <person1> to the family tree at level <level> who is <male>
    And I add a person named <person2> to the family tree at level <childrenLevel> who is <female>
    And I connect <person2> as a child to <person1>
    And I add a person named <person3> to the family tree at level <childrenLevel> who is <male>
    And I connect <person3> as a child to <person1>
    And I add a person named <person4> to the family tree at level <grandChildLevel> who is <male>
    And I connect <person4> as a child to <person2>
    And I add a person named <person5> to the family tree at level <grandChildLevel> who is <female>
    And I connect <person5> as a child to <person3>
    Then <person4> is cousin of <person5>
    Examples:
    | person1 | person2  | person3  | person4 | person5  | male | female | level | childrenLevel | grandChildLevel |
    | Chris   | Isabella | Michael  | Jon     | Anabelle | male | female | 1     | 2             | 3               |

  Scenario Outline: A person is aunt or uncle of another person
    Given I add a person named <person1> to the family tree at level <level> who is <male>
    And I add a person named <person2> to the family tree at level <childrenLevel> who is <female>
    And I connect <person2> as a child to <person1>
    And I add a person named <person3> to the family tree at level <childrenLevel> who is <male>
    And I connect <person3> as a child to <person1>

    And I add a person named <person4> to the family tree at level <childrenLevel> who is <male>
    And I let <person2> and <person4> getting married
    And I add a person named <person5> to the family tree at level <childrenLevel> who is <female>
    And I let <person3> and <person5> getting married

    And I add a person named <person6> to the family tree at level <grandChildLevel> who is <male>
    And I connect <person6> as a child to <person2>
    And I connect <person6> as a child to <person4>
    And I add a person named <person7> to the family tree at level <grandChildLevel> who is <female>
    And I connect <person7> as a child to <person3>
    And I connect <person7> as a child to <person5>
    Then <person2> is aunt of <person7>
    And <person3> is uncle of <person6>
    Examples:
      | person1 | person2  | person3  | person4 | person5  | person6 | person7 | male | female | level | childrenLevel | grandChildLevel |
      | Chris   | Isabella | Michael  | Jon     | Anabelle | Richi   | Ellen   | male | female | 1     | 2             | 3               |

  Scenario Outline: A person is grandparent of another person
    Given I add a person named <person1> to the family tree at level <level> who is <male>
    And I add a person named <person2> to the family tree at level <level> who is <female>
    And I let <person1> and <person2> getting married
    And I add a person named <person3> to the family tree at level <childrenLevel> who is <male>
    And I connect <person3> as a child to <person1>
    And I connect <person3> as a child to <person2>

    And I add a person named <person4> to the family tree at level <grandChildLevel> who is <childGender>
    And I connect <person4> as a child to <person3>

    Then <person1> is grandfather of <person4>
    And <person2> is grandmother of <person4>
    And <person4> is <grandchild> of <person1>
    And <person4> is <grandchild> of <person2>
    Examples:
      | person1 | person2  | person3  | person4  | male | female | level | childrenLevel | grandChildLevel | grandchild    | childGender |
      | Chris   | Isabella | Michael  | Jon      | male | female | 1     | 2             | 3               | grandson      | male        |
      | Chris   | Isabella | Michael  | Anabelle | male | female | 1     | 2             | 3               | granddaughter | female      |