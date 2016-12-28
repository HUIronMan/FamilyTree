package app;

/*
 * Created by kevintrogant on 15.12.16.
 */
public class Stammbaeume {

    public static void main(String[] args) {
        System.out.println("Stammbäume");


        UI ui = new UI();
        /* Case does not matter for commands (Only for names)
         */
        ui.parseCommand("INSERT PERSON Bob MALE");
        ui.parseCommand("INSERT PERSON Eva FEMALE");
        ui.parseCommand("INSERT PERSON Tom MALE");
        ui.parseCommand("INSERT PERSON Tim MALE");
        ui.parseCommand("INSERT PERSON Timon MALE");
        ui.parseCommand("INSERT PERSON Pumba MALE");
        ui.parseCommand("INSERT PERSON Tina FEMALE");
        ui.parseCommand("MAKE Tom CHILD OF Bob");
        ui.parseCommand("MAKE Tom CHILD OF Eva");
        ui.parseCommand("MAKE Tim CHILD OF Bob");
        ui.parseCommand("MAKE Timon CHILD OF Tom");
        ui.parseCommand("MAKE Pumba CHILD OF Tom");
        ui.parseCommand("MAKE Tina CHILD OF Tim");
        ui.parseCommand("MARRY Bob WITH Eva");

        System.out.println("Structured tree:");
        FamilyTree.getInstance().print();

        System.out.println(FamilyTree.getInstance().isParentOf("Bob", "Tom"));
        System.out.println(FamilyTree.getInstance().getSpouse("Bob").getName());

        System.out.println(FamilyTree.getInstance().getChildrenOf("Eva"));
        System.out.println(FamilyTree.getInstance().getGrandchildrenOf("Bob"));
        System.out.println(FamilyTree.getInstance().getGrandparentsOf("Timon"));

        System.out.println(FamilyTree.getInstance().getCousinsOf("Tina"));

        System.out.println(FamilyTree.getInstance().getFathersSiblings("Tina"));

    }
}
