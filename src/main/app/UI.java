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
     * RESET
     * GET ... OF name
     * IS name ... OF name
     *
     * @param command The command
     */
    public boolean parseCommand(String command) {
        String com = "";
        boolean done = false;

        for (int i = 0; i < command.length(); i++) {
            com += command.charAt(i);
            if (com.toUpperCase().equals("INSERT PERSON ")) {
                done = parseInsertPerson(command, i + 1);
                break;
            } else if (com.toUpperCase().equals("MAKE ")) {
                done = parseMakeChildOf(command, i + 1);
                break;
            } else if (com.toUpperCase().equals("MARRY ")) {
                done = parseMarryWith(command, i + 1);
                break;
            } else if (com.toUpperCase().equals("PRINT TREE")) {
                done = true;
                FamilyTree.getInstance().print();
                break;
            } else if (com.toUpperCase().equals("GET ")) {
                done = parseGetCommand(command, i + 1);
                break;
            } else if (com.toUpperCase().equals("IS ")) {
                done = parseIsCommand(command, i + 1);
                break;
            } else if (com.toUpperCase().equals("RESET")) {
                FamilyTree.getInstance().reset();
                done = true;
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

    // GET ... OF name
    private boolean parseGetCommand(String command, int start) {
        if (start >= command.length()) {
            System.out.println("GET: Missing parameters: Relation and name");
            return false;
        }
        String[] params = command.substring(start).split(" ");
        if (params.length < 3) {
            System.out.println("GET: Missing parameters: Relation and name");
            return false;
        }
        if (!params[1].toUpperCase().equals("OF")) {
            System.out.println("Syntax error: Expected OF");
            return false;
        }
        switch (params[0].toUpperCase()) {
            case "SPOUSE": {
                Person spouse = FamilyTree.getInstance().getSpouse(params[2]);
                System.out.println(spouse);
                break;
            }
            case "PARENTS": {
                System.out.println(FamilyTree.getInstance().getParentsOf(params[2]));
                break;
            }
            case "CHILDREN": {
                System.out.println(FamilyTree.getInstance().getChildrenOf(params[2]));
                break;
            }
            case "SIBLINGS": {
                System.out.println(FamilyTree.getInstance().getSiblingsOf(params[2]));
                break;
            }
            case "GRANDPARENTS": {
                System.out.println(FamilyTree.getInstance().getGrandparentsOf(params[2]));
                break;
            }
            case "GRANDCHILDREN": {
                System.out.println(FamilyTree.getInstance().getGrandchildrenOf(params[2]));
                break;
            }
            case "COUSINS": {
                System.out.println(FamilyTree.getInstance().getCousinsOf(params[2]));
                break;
            }
            case "FATHERS_SIBLINGS": {
                System.out.println(FamilyTree.getInstance().getFathersSiblings(params[2]));
                break;
            }
            case "MOTHERS_SIBLINGS": {
                System.out.println(FamilyTree.getInstance().getMothersSiblings(params[2]));
                break;
            }
            default:
                System.out.println("GET: Unknown relation \"" + params[0] + "\"");
                return false;
        }
        return true;
    }

    // IS name1 ... OF name2
    private boolean parseIsCommand(String command, int start) {
        if (start >= command.length()) {
            System.out.println("IS: Missing parameters: name1, Relation and name2");
            return false;
        }
        String[] params = command.substring(start).split(" ");
        if (params.length < 4) {
            System.out.println("IS: Missing parameters: name1, Relation and name");
            return false;
        }
        if (!params[2].toUpperCase().equals("OF")) {
            System.out.println("Syntax error: Expected OF");
            return false;
        }
        boolean result;
        switch (params[1].toUpperCase()) {
            case "SPOUSE":
                result = FamilyTree.getInstance().isSpouseOf(params[0], params[3]);
                break;
            case "PARENT": {
                result = FamilyTree.getInstance().isParentOf(params[0], params[3]);
                break;
            }
            case "CHILD": {
                result = FamilyTree.getInstance().isChildOf(params[0], params[3]);
                break;
            }
            case "SIBLING": {
                result = FamilyTree.getInstance().isSiblingOf(params[0], params[3]);
                break;
            }
            case "GRANDPARENT": {
                result = FamilyTree.getInstance().isGrandparentOf(params[0], params[3]);
                break;
            }
            case "GRANDCHILD": {
                result = FamilyTree.getInstance().isGrandchildOf(params[0], params[3]);
                break;
            }
            case "COUSIN": {
                result = FamilyTree.getInstance().isCousinOf(params[0], params[3]);
                break;
            }
            case "FATHERS_SIBLING": {
                result = FamilyTree.getInstance().isFathersSibling(params[0], params[3]);
                break;
            }
            case "MOTHERS_SIBLING": {
                result = FamilyTree.getInstance().isMothersSibling(params[0], params[3]);
                break;
            }
            default:
                System.out.println("IS: Unknown relation \"" + params[1] + "\"");
                return false;
        }
        if (result) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        return true;
    }
}
