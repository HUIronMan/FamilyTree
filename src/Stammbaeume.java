/*
 * Created by kevintrogant on 15.12.16.
 */
public class Stammbaeume {

    public static void main(String[] args) {
        System.out.println("Stammbäume");

        Person bob = new Person("Bob", Gender.MALE);
        Person eva = new Person( "Eva", Gender.FEMALE);
        Person tom = new Person("Tom", Gender.MALE);

        FamilyTree.getInstance().addPerson(bob);
        FamilyTree.getInstance().addPerson(eva);
        FamilyTree.getInstance().addPerson(tom);

        try {
            FamilyTree.getInstance().marryPersons("Bob", "Eva");
            FamilyTree.getInstance().makeChildOf(tom, bob);

            System.out.println(FamilyTree.getInstance().isParentOf(bob, tom));
            System.out.println(FamilyTree.getInstance().getSpouse(bob).getName());

        } catch (InvalidRelationshipException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
