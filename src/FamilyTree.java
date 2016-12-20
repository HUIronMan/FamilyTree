/*
 * Created by kevintrogant on 15.12.16.
 */

import org.apache.regexp.RE;

import java.util.HashMap;
import java.util.LinkedList;

class FamilyTree {

    /* Singleton stuff */

    private static FamilyTree instance = null;

    /* There is only going to be one FamilyTree on runtime anyways,
     * so against better judgment, we use a singleton
     */
    static FamilyTree getInstance() {
        if (instance == null) {
            instance = new FamilyTree();
        }
        return instance;
    }

    private FamilyTree() {
        persons = new HashMap<>();
        relations = new LinkedList<>();
    }

    /* End of Singleton stuff */

    private HashMap<String, Person> persons;
    private LinkedList<Relation> relations;

    /** \brief Reset the tree */
    void reset() {
        persons.clear();
        relations.clear();
    }

    void addPerson(Person p) {
        persons.put(p.getName(), p);
    }

    Person getPerson(String name) {
        return persons.get(name);
    }

    /* Query the tree */

    boolean isPersonMarried(String name) {
        return isPersonMarried(getPerson(name));
    }

    boolean isPersonMarried(Person p) {
        for (Relation r : relations) {
            if ((r.getPersonA().equals(p) || r.getPersonB().equals(p)) && r.getType() == RelationType.MARRIED) {
                return true;
            }
        }
        return false;
    }

    Person getSpouse(String nameOfP) {
        Person p = getPerson(nameOfP);
        return getSpouse(p);
    }

    Person getSpouse(Person p) {
        for (Relation r : relations) {
            if (r.getPersonA().equals(p) && r.getType() == RelationType.MARRIED) {
                return r.getPersonB();
            } else if (r.getPersonB().equals(p) && r.getType() == RelationType.MARRIED) {
                return r.getPersonA();
            }
        }
        return null;
    }

    Person getParentOf(String name) {
        Person p = getPerson(name);
        return getParentOf(p);
    }

    Person getParentOf(Person p) {
        for (Relation r : relations) {
            if (r.getPersonA().equals(p) && r.getType() == RelationType.CHILD_OF) {
                return r.getPersonB();
            }
        }
        return null;
    }

    LinkedList<Person> getChildrenOf(String name) {
        Person p = getPerson(name);
        return getChildrenOf(p);
    }

    LinkedList<Person> getChildrenOf(Person p) {
        LinkedList<Person> children = new LinkedList<>();
        for (Relation r : relations) {
            if (r.getPersonB().equals(p) && r.getType() == RelationType.CHILD_OF) {
                children.addLast(r.getPersonA());
            }
        }
        return children;
    }

    boolean isParentOf(String nameParent, String nameChild) {
        Person parent = getPerson(nameParent);
        Person child = getPerson(nameChild);
        return isParentOf(parent, child);
    }

    boolean isParentOf(Person parent, Person child) {
        Person p = getParentOf(child);
        if (p == null)
            return false;
        return p.equals(parent);
    }

    boolean isChildOf(String nameChild, String nameParent) {
        Person child = getPerson(nameChild);
        Person parent = getPerson(nameParent);
        return isChildOf(child, parent);
    }

    boolean isChildOf(Person child, Person parent) {
        return isParentOf(parent, child);
    }

    /* Modify the tree */

    void marryPersons(String nameA, String nameB) throws InvalidRelationshipException {
        marryPersons(getPerson(nameA), getPerson(nameB));
    }

    /** \brief Marry two unrelated persons
     *
     * Both must not be married.
     *
     */
    void marryPersons(Person pA, Person pB) throws InvalidRelationshipException {
        if (isPersonMarried(pA)) {
            throw new InvalidRelationshipException(pA, pB, RelationType.MARRIED, pA.getName() + " is already married");
        }
        if (isPersonMarried(pB)) {
            throw new InvalidRelationshipException(pA, pB, RelationType.MARRIED, pB.getName() + " is already married");
        }

        relations.addLast(new Relation(pA, pB, RelationType.MARRIED));
    }

    void makeChildOf(String nameChild, String nameParent) throws InvalidRelationshipException {
        Person child = getPerson(nameChild);
        Person parent = getPerson(nameParent);
        makeChildOf(child, parent);
    }

    /** \brief Make a person a child of another person
     *
     * This is not allowed to introduce a cycle (ie. child mustn't be the parent of parent)
     */
    void makeChildOf(Person child, Person parent) throws InvalidRelationshipException {
        if (isChildOf(child, parent))
            return; // nothing to do here

        if (isChildOf(parent, child)) {
            throw new InvalidRelationshipException(child, parent, RelationType.CHILD_OF, parent.getName() + " is a child of " + child.getName());
        }

        relations.addLast(new Relation(child, parent, RelationType.CHILD_OF));
    }
}
