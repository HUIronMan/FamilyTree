package app;

/**
 * Created by kevintrogant on 27.12.16.
 */
public class UI {
    /** \brief Parse a text command
     *
     * Available commands are:
     * INSERT PERSON name gender
     * MAKE name CHILD OF name
     * MARRY name WITH name
     * PRINT TREE
     *
     * @param command The command
     */
    public boolean parseCommand(String command) {
        String com = "";
        boolean done = false;

        for (int i = 0; i < command.length() - 1; i++) {
            com += command.charAt(i);
            if (command.charAt(i + 1) != ' ') {
                continue;
            }
            if (com.toUpperCase().equals("INSERT PERSON")) {
                i++;
                done = parseInsertPerson(command, i + 1);
                break;
            } else if (com.toUpperCase().equals("MAKE")) {
                i++;
                done = parseMakeChildOf(command, i + 1);
                break;
            } else if (com.toUpperCase().equals("MARRY")) {
                i++;
                done = parseMarryWith(command, i + 1);
                break;
            } else if (com.toUpperCase().equals("PRINT TREE")) {
                done = true;
                FamilyTree.getInstance().print();
                break;
            }
        }
        if (!done) {
            System.out.println("Unable to parse command \"" + command + "\"");
        }
        return done;
    }

    // INSERT PERSON <start>name gender
    private boolean parseInsertPerson(String command, int start) {
        if (start >= command.length()) {
            System.out.println("INSERT PERSON: Missing parameters name and gender");
            return false;
        }
        String[] params = command.substring(start).split(" ");
        if (params.length < 2) {
            System.out.println("INSERT PERSON: Missing parameters name and gender");
            return false;
        }
        if (params[1].toLowerCase().equals("male")) {
            FamilyTree.getInstance().addPerson(new Person(params[0], Gender.MALE));
            return true;
        } else if (params[1].toLowerCase().equals("female")) {
            FamilyTree.getInstance().addPerson(new Person(params[0], Gender.FEMALE));
            return true;
        } else {
            System.out.println("INSERT PERSON: \"" + params[1] + "\" is not a gender");
        }

        return false;
    }

    // MAKE <start>name CHILD OF name
    private boolean parseMakeChildOf(String command, int start) {
        if (start >= command.length()) {
            System.out.println("MAKE CHILD OF: Missing parameters name of child and name of parent");
            return false;
        }
        String[] params = command.substring(start).split(" ");
        if (params.length < 4) {
            System.out.println("MAKE CHILD OF: Missing parameters name of child and name of parent");
            return false;
        }
        // name of child: params[0] name of parent: params[3]
        if (!params[1].toUpperCase().equals("CHILD")) {
            System.out.println("Syntax error: Expected CHILD OF");
            return false;
        } else if (!params[2].toUpperCase().equals("OF")) {
            System.out.println("Syntax error: Expected CHILD OF");
            return false;
        }
        try {
            FamilyTree.getInstance().makeChildOf(params[0], params[3]);
        } catch (InvalidRelationshipException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    // MARRY <start>name WITH name
    private boolean parseMarryWith(String command, int start) {
        if (start >= command.length()) {
            System.out.println("MARRY WITH: Missing names");
            return false;
        }
        String[] params = command.substring(start).split(" ");
        if (params.length < 3) {
            System.out.println("MARRY WITH: Missing names");
            return false;
        }
        // names: params[0] and params[2]
        if (!params[1].toUpperCase().equals("WITH")) {
            System.out.println("Syntax error: Expected WITH");
            return false;
        }
        try {
            FamilyTree.getInstance().marryPersons(params[0], params[2]);
        } catch (InvalidRelationshipException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
