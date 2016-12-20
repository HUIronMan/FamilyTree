/**
 * Created by kevintrogant on 15.12.16.
 */
class Person {

   private String name;
   private Gender gender;

   /** \brief Constructor.
    *
    * Calculates the fnv1a hash of the name for quicker access to persons
    * The constructor does not(!) add the person to the FamilyTree.
    *
    * @param name Name of the person
    */
   Person(String name, Gender gender) {
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
}

enum Gender {
   MALE,
   FEMALE
}