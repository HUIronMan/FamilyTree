package app;

/*
 * Created by kevintrogant on 15.12.16.
 */
public class Stammbaeume {

    public static void main(String[] args) {
        System.out.println("Stammbäume");


        FamilyTree.getInstance().addPerson(new Person("Bob", Gender.MALE));
        FamilyTree.getInstance().addPerson(new Person("Eva", Gender.FEMALE));
        FamilyTree.getInstance().addPerson(new Person("Tom", Gender.MALE));
        FamilyTree.getInstance().addPerson(new Person("Tim", Gender.MALE));
        FamilyTree.getInstance().addPerson(new Person("Timon", Gender.MALE));
        FamilyTree.getInstance().addPerson(new Person("Pumba", Gender.MALE));
        FamilyTree.getInstance().addPerson(new Person("Tina", Gender.FEMALE));

        try {
            FamilyTree.getInstance().marryPersons("Bob", "Eva");
            FamilyTree.getInstance().makeChildOf("Tom", "Bob");
            FamilyTree.getInstance().makeChildOf("Tom", "Eva");
            FamilyTree.getInstance().makeChildOf("Tim", "Bob");
            FamilyTree.getInstance().makeChildOf("Timon", "Tom");
            FamilyTree.getInstance().makeChildOf("Pumba", "Tom");
            FamilyTree.getInstance().makeChildOf("Tina", "Tim");

            System.out.println("Structured tree:");
            FamilyTree.getInstance().print();

            System.out.println(FamilyTree.getInstance().isParentOf("Bob", "Tom"));
            System.out.println(FamilyTree.getInstance().getSpouse("Bob").getName());

            System.out.println(FamilyTree.getInstance().getChildrenOf("Eva"));
            System.out.println(FamilyTree.getInstance().getGrandchildrenOf("Bob"));
            System.out.println(FamilyTree.getInstance().getGrandparentsOf("Timon"));

            System.out.println(FamilyTree.getInstance().getCousinsOf("Tina"));

            System.out.println(FamilyTree.getInstance().getFathersSiblings("Tina"));

        } catch (InvalidRelationshipException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
