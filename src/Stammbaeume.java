import java.util.LinkedList;

/**
 * Created by kevintrogant on 15.12.16.
 */
public class Stammbaeume {

    public static void main(String[] args) {
        System.out.println("Stammbäume");
        for (int i = 0; i < 5000; i++) {
            Person p = new Person("foo", "bar", String.valueOf(i));
            FamilyTree.getInstance().registerPerson(p);
        }
        long[] ids = FamilyTree.getInstance().getPersonsWithName("foo bar");

        LinkedList<Long> seenIds = new LinkedList<Long>();

        for (long id : ids) {
            Person p = FamilyTree.getInstance().getPerson(id);
            System.out.print(p.getFullName());
            System.out.print(" ");
            System.out.print(p.getUniqueIDString());
            System.out.print(" ");
            System.out.println(p.getBirthday());

            assert ! seenIds.contains(p.getUniqueID());
            seenIds.addLast(p.getUniqueID());
        }
    }
}
