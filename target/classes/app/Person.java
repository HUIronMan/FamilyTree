package app;

/**
 * Created by kevintrogant on 15.12.16.
 */
public class Person {

   private String name;
   private Gender gender;

   /** \brief Constructor.
    *
    * Calculates the fnv1a hash of the name for quicker access to persons
    * The constructor does not(!) add the person to the FamilyTree.
    *
    * @param name Name of the person
    */
   public Person(String name, Gender gender) {
      this.name = name;
      this.gender = gender;
   }

   String getName() {
      return name;
   }

   Gender getGender() {
      return gender;
   }

   boolean equals(Person other) {
      return name.equals(other.getName());
   }

   @Override
   public String toString() {
      return this.name + " " + gender.toString();
   }
}

//public enum Gender {
//   MALE,
//   FEMALE
//}