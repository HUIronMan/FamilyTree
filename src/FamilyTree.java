/**
 * Created by kevintrogant on 15.12.16.
 */

import java.util.HashMap;
import java.util.LinkedList;

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
        personIdSuffixes = new HashMap<>();
        nameIDMap = new HashMap<>();
    }

    /* End of Singleton stuff */

    private HashMap<Long, Person> persons;
    private HashMap<Long, Integer> personIdSuffixes;

    private HashMap<String, LinkedList<Long>> nameIDMap;

    boolean doesPersonExist(long id) {
        return persons.containsKey(id);
    }

    int getIdSuffix(long id) {
        int suffix = 1;
        if (personIdSuffixes.containsKey(id)) {
            suffix = personIdSuffixes.get(id) + 1;
            personIdSuffixes.replace(id, suffix);
        } else{
            personIdSuffixes.put(id, suffix);
        }
        return suffix;
    }

    void registerPerson(Person p) {
        persons.put(p.getUniqueID(), p);
        String fullName = p.getFullName();
        if (!nameIDMap.containsKey(fullName)) {
            nameIDMap.put(fullName, new LinkedList<>());
        }
        nameIDMap.get(fullName).addLast(p.getUniqueID());
    }

    void unregisterPerson(long id) {
        nameIDMap.get(persons.get(id).getFullName()).remove(id);
        persons.remove(id);
    }

    Person getPerson(long id) {
        return persons.get(id);
    }

    long[] getPersonsWithName(String fullName) {
        if (nameIDMap.containsKey(fullName)) {
            long[] ids = new long[nameIDMap.get(fullName).size()];
            for (int i = 0; i < nameIDMap.get(fullName).size(); i++) {
                ids[i] = nameIDMap.get(fullName).get(i);
            }
            return ids;
        } else {
            return new long[0];
        }
    }
}
