/*
 * Created by kevintrogant on 15.12.16.
 */
package java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

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

    void print() {
        // find a starting point
        for (Person p : persons.values()) {
            if (getParentsOf(p).size() == 0) {
                printPerson(p, 0);
            }
        }
    }

    private void printPerson(Person p, int d) {
        for (int i = 0; i < d; i++)
            System.out.print(" ");
        System.out.print(p);
        if (getSpouse(p) != null) {
            System.out.print(" married with " + getSpouse(p).toString());
        }
        System.out.print("\n");
        for (Person child : getChildrenOf(p)) {
            printPerson(child, d+2);
        }
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

    Set<Person> getParentsOf(String name) {
        Person p = getPerson(name);
        return getParentsOf(p);
    }

    Set<Person> getParentsOf(Person p) {
        HashSet<Person> parents = new HashSet<>();
        for (Relation r : relations) {
            if (r.getPersonA().equals(p) && r.getType() == RelationType.CHILD_OF) {
                parents.add(r.getPersonB());
            }
        }
        return parents;
    }

    Set<Person> getChildrenOf(String name) {
        Person p = getPerson(name);
        return getChildrenOf(p);
    }

    Set<Person> getChildrenOf(Person p) {
        HashSet<Person> children = new HashSet<>();
        for (Relation r : relations) {
            if (r.getPersonB().equals(p) && r.getType() == RelationType.CHILD_OF) {
                children.add(r.getPersonA());
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
        Set<Person> p = getParentsOf(child);
        return p.contains(parent);
    }

    boolean isChildOf(String nameChild, String nameParent) {
        Person child = getPerson(nameChild);
        Person parent = getPerson(nameParent);
        return isChildOf(child, parent);
    }

    boolean isChildOf(Person child, Person parent) {
        return isParentOf(parent, child);
    }

    /* More complex queries */

    Set<Person> getSiblingsOf(String name) {
        Person p = getPerson(name);
        return getSiblingsOf(p);
    }

    Set<Person> getSiblingsOf(Person person) {
        HashSet<Person> siblings = new HashSet<>();

        // Get the children of my parents, remove myself -> list of siblings

        Set<Person> parents = getParentsOf(person);
        for (Person parent : parents) {
            Set<Person> s = getChildrenOf(parent);
            s.remove(person);
            siblings.addAll(s);
        }

        return siblings;
    }

    Set<Person> getGrandparentsOf(String name) {
        Person p = getPerson(name);
        return getGrandparentsOf(p);
    }

    Set<Person> getGrandparentsOf(Person person) {
        Set<Person> parents = getParentsOf(person);
        HashSet<Person> grandparents = new HashSet<>();
        for (Person parent : parents) {
            grandparents.addAll(getParentsOf(parent));
        }
        return grandparents;
    }

    Set<Person> getGrandchildrenOf(String name) {
        Person p = getPerson(name);
        return getGrandchildrenOf(p);
    }

    Set<Person> getGrandchildrenOf(Person person) {
        Set<Person> children = getChildrenOf(person);
        HashSet<Person> grandchildren = new HashSet<>();
        for (Person child : children) {
            grandchildren.addAll(getChildrenOf(child));
        }
        return grandchildren;
    }

    Set<Person> getCousinsOf(String name) {
        Person p = getPerson(name);
        return getCousinsOf(p);
    }

    Set<Person> getCousinsOf(Person person) {
        // My parents siblings children are my cousins
        HashSet<Person> cousins = new HashSet<>();
        for (Person parent : getParentsOf(person)) {
            for (Person sib : getSiblingsOf(parent)) {
                cousins.addAll(getChildrenOf(sib));
            }
        }
        return cousins;
    }

    Set<Person> getFathersSiblings(String name) {
        Person p = getPerson(name);
        return getFathersSiblings(p);
    }

    Set<Person> getFathersSiblings(Person person) {
        Set<Person> parents = getParentsOf(person);
        HashSet<Person> siblings = new HashSet<>();

        parents.forEach(parent -> {
            if (parent.getGender() == Gender.MALE) {
                siblings.addAll(getSiblingsOf(parent));
            }
        });
        return siblings;
    }

    Set<Person> getMothersSiblings(String name) {
        Person p = getPerson(name);
        return getMothersSiblings(p);
    }

    Set<Person> getMothersSiblings(Person person) {
        Set<Person> parents = getParentsOf(person);
        HashSet<Person> siblings = new HashSet<>();

        parents.forEach(parent -> {
            if (parent.getGender() == Gender.FEMALE) {
                siblings.addAll(getSiblingsOf(parent));
            }
        });
        return siblings;
    }

    /* Modify the tree */

    void addPerson(Person p) {
        persons.put(p.getName(), p);
    }

    Person getPerson(String name) {
        return persons.get(name);
    }

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
     * And any person can only have up to two parents
     */
    void makeChildOf(Person child, Person parent) throws InvalidRelationshipException {
        if (isChildOf(child, parent))
            return; // nothing to do here

        if (isChildOf(parent, child)) {
            throw new InvalidRelationshipException(child, parent, RelationType.CHILD_OF, parent.getName() + " is a child of " + child.getName());
        }

        if (getParentsOf(child).size() == 2) {
            throw new InvalidRelationshipException(child, parent, RelationType.CHILD_OF, child.getName() + " already has two parents");
        }

        relations.addLast(new Relation(child, parent, RelationType.CHILD_OF));
    }
}
