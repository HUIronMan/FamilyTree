/**
 * Created by kevintrogant on 15.12.16.
 */
public class Person {

    private String[] firstNames;
    private String lastName;
    private String bDay;

    private long uniqueID;
    private int idSuffix;

    private long calculateUniqueID() {
        String str = "";
        for (String fName : firstNames){
            str += fName + " ";
        }
        str += lastName + "-" + String.valueOf(idSuffix);
        uniqueID = FNV.FNV1a(str.getBytes());
        return uniqueID;
    }

    Person(String fName, String lName, String birthday) {
        firstNames = new String[1];
        firstNames[0] = fName;
        lastName = lName;
        bDay = birthday;

        idSuffix = 0;
        calculateUniqueID();

        while (FamilyTree.getInstance().doesPersonExist(uniqueID)) {
            idSuffix = FamilyTree.getInstance().getIdSuffix(uniqueID);
            calculateUniqueID();
        }

        FamilyTree.getInstance().registerPerson(this);
    }

    Person(String[] fNames, String lName, String birthday) {
        firstNames = fNames;
        lastName = lName;
        bDay = birthday;

        idSuffix = 0;
        calculateUniqueID();

        while (FamilyTree.getInstance().doesPersonExist(uniqueID)) {
            idSuffix = FamilyTree.getInstance().getIdSuffix(uniqueID);
            calculateUniqueID();
        }

        FamilyTree.getInstance().registerPerson(this);
    }

    String getUniqueIDString() {
        return String.format("%x", uniqueID);
    }

    long getUniqueID() {
        return uniqueID;
    }

    String getFullName() {
        String name = "";
        for (String fName : firstNames)
            name += fName + " ";
        return name + lastName;
    }

    String getBirthday() {
        return bDay;
    }
}
