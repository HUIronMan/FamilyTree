package app;

/**
 * Created by kevintrogant on 20.12.16.
 */
public class InvalidRelationshipException extends Exception {

    private Person personA;
    private Person personB;
    private RelationType intendedType;
    private String message = "";

    public InvalidRelationshipException(Person personA, Person personB, RelationType intendedType) {
        this.personA = personA;
        this.personB = personB;
        this.intendedType = intendedType;
    }

    public InvalidRelationshipException(Person personA, Person personB, RelationType intendedType, String addMessage) {
        this.personA = personA;
        this.personB = personB;
        this.intendedType = intendedType;
        this.message = addMessage;
    }

    @Override
    public String getMessage() {
        switch (intendedType) {
            case CHILD_OF:
                return "Trying to make " + personA.getName() + " a child of " + personB.getName() + ": " + message;
            case MARRIED:
                return "Trying to marry " + personA.getName() + " and " + personB.getName() + ": " + message;
        }
        return "???";
    }
}
