/*
 * Created by kevintrogant on 15.12.16.
 */
public class Stammbaeume {

    public static void main(String[] args) {
        System.out.println("Stammbäume");


        FamilyTree.getInstance().addPerson(new Person("Bob", Gender.MALE));
        FamilyTree.getInstance().addPerson(new Person("Eva", Gender.FEMALE));
        FamilyTree.getInstance().addPerson(new Person("Tom", Gender.MALE));

        try {
            FamilyTree.getInstance().marryPersons("Bob", "Eva");
            FamilyTree.getInstance().makeChildOf("Tom", "Bob");

            System.out.println(FamilyTree.getInstance().isParentOf("Bob", "Tom"));
            System.out.println(FamilyTree.getInstance().getSpouse("Bob").getName());

        } catch (InvalidRelationshipException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
