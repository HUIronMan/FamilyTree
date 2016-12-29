/*
 * Created by kevintrogant on 15.12.16.
 */
package app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class FamilyTree {

    /* Singleton stuff */

    private static FamilyTree instance = null;

    /* There is only going to be one FamilyTree on runtime anyways,
     * so against better judgment, we use a singleton
     */
    public static FamilyTree getInstance() {
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

    boolean isSpouseOf(String nameA, String nameB) {
        Person pA = getPerson(nameA);
        Person pB = getPerson(nameB);
        return isSpouseOf(pA, pB);
    }

    boolean isSpouseOf(Person personA, Person personB) {
        return getSpouse(personA).equals(personB);
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

    // is A a sibling of B ?
    boolean isSiblingOf(String nameA, String nameB) {
        Person pA = getPerson(nameA);
        Person pB = getPerson(nameB);
        return isSiblingOf(pA, pB);
    }

    boolean isSiblingOf(Person personA, Person personB) {
        if (personA == null || personB == null) {
            return false;
        }
        Set<Person> siblings = getSiblingsOf(personB);
        return siblings.contains(personA);
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

    // is A a grandparent of B?
    boolean isGrandparentOf(String nameA, String nameB) {
        Person pA = getPerson(nameA);
        Person pB = getPerson(nameB);
        return isGrandparentOf(pA, pB);
    }

    boolean isGrandparentOf(Person personA, Person personB) {
        if (personA == null || personB == null) {
            return false;
        }
        Set<Person> grandparents = getGrandparentsOf(personB);
        return grandparents.contains(personA);
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

    // is A a grandchild of B?
    boolean isGrandchildOf(String nameA, String nameB) {
        return isGrandparentOf(nameB, nameA);
    }

    boolean isGrandchildOf(Person personA, Person personB) {
        return isGrandparentOf(personB, personA);
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

    // is A a cousing of B?
    boolean isCousinOf(String nameA, String nameB) {
        Person pA = getPerson(nameA);
        Person pB = getPerson(nameB);
        return isChildOf(pA, pB);
    }

    boolean isCousinOf(Person personA, Person personB) {
        if (personA == null || personB == null) {
            return false;
        }
        Set<Person> cousins = getCousinsOf(personB);
        return cousins.contains(personA);
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

    // Is A a sibling of the father of B?
    boolean isFathersSibling(String nameA, String nameB) {
        Person pA = getPerson(nameA);
        Person pB = getPerson(nameB);
        return isFathersSibling(pA, pB);
    }

    boolean isFathersSibling(Person personA, Person personB) {
        if (personA == null || personB == null) {
            return false;
        }
        Set<Person> fathersSiblings = getFathersSiblings(personB);
        return fathersSiblings.contains(personA);
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

    // Is A a sibling of the mother of B?
    boolean isMothersSibling(String nameA, String nameB) {
        Person pA = getPerson(nameA);
        Person pB = getPerson(nameB);
        return isMothersSibling(pA, pB);
    }

    boolean isMothersSibling(Person personA, Person personB) {
        if (personA == null || personB == null) {
            return false;
        }
        Set<Person> mothersSiblings = getMothersSiblings(personB);
        return mothersSiblings.contains(personA);
    }

    /* Modify the tree */

    public void addPerson(Person p) {
        persons.put(p.getName(), p);
    }

    Person getPerson(String name) {
        if (!persons.containsKey(name)) {
            System.out.println("Person \"" + name + "\" does not exist!");
        }
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
