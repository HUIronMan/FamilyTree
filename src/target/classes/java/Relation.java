package java;/*
 * Created by kevintrogant on 20.12.16.
 */

public class Relation {
    private Person personA;
    private Person personB;

    private RelationType type;

    Relation(String nameA, String nameB, RelationType type) {
        Person pA = FamilyTree.getInstance().getPerson(nameA);
        assert pA != null;
        Person pB = FamilyTree.getInstance().getPerson(nameB);
        assert pB != null;

        personA = pA;
        personB = pB;
        this.type = type;
    }

    Relation(Person personA, Person personB, RelationType type) {
        this.personA = personA;
        this.personB = personB;
        this.type = type;
    }

    Person getPersonA() {
        return personA;
    }

    Person getPersonB() {
        return personB;
    }

    RelationType getType() {
        return type;
    }
}

enum RelationType {
    MARRIED,
    CHILD_OF, // A is child of B
}