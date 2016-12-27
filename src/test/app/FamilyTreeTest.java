package app;

//import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
//import org.junit.After;   // Does not execute Before and After
//import org.junit.Before;  // Does not execute Before and After
import cucumber.api.java.Before;
import cucumber.api.java.After;


public class FamilyTreeTest {
    private Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
//        scenario.write("This goes into the report(s)\n");
//        FamilyTree.getInstance().reset();
    }

//    @Given("^I have (\\d+) people in my family tree$")
//    public void i_have_a_number_of_people_in_my_family_tree(int amount) throws Throwable {
//        int people = FamilyTree.getInstance().persons.size();
//        throw new PendingException();
//    }

    @Given("^I add a person named ([^\"]*) to the family tree at level (\\d+) who is ([^\"]*)$")
    public void i_add_a_person_to_my_family_tree(String name, int level, String gender) throws Throwable {
       Gender gen = (gender == "female") ? Gender.FEMALE : Gender.MALE;
       Person person = new Person(name, gen);
       FamilyTree.getInstance().addPerson(person);
    }

    @Given("^I connect ([^\"]*) as a child to ([^\"]*)")
    public void i_connect_a_child_to_a_parent(String child, String parent) throws Throwable {
        FamilyTree.getInstance().makeChildOf(child, parent);
    }

    @Given("^I let ([^\"]*) and ([^\"]*) getting married$")
    public void i_let_romeo_and_juliette_getting_married(String romeo, String juliette) throws Throwable {
        FamilyTree.getInstance().marryPersons(romeo, juliette);
    }

    @Given("^I have a root ([^\"]*) at level (\\d+)$")
    public void i_have_an_endboss_in_my_family_tree(String rootPerson, int level) throws Throwable {
        Assert.assertTrue(FamilyTree.getInstance().getParentsOf(rootPerson)==null);
    }

    @Then("^([^\"]*) is in my family tree$")
    public void a_person_is_in_my_family_tree(String name) throws Throwable {
        Person person = FamilyTree.getInstance().getPerson(name);
        Assert.assertTrue(person.getName().equals(name));
//        throw new PendingException();
    }

    @Then("^([^\"]*) is married to ([^\"]*)$")
    public void a_person_is_married_to_person(String person1, String person2) throws Throwable {
        Person person = FamilyTree.getInstance().getSpouse(person2);
        Assert.assertTrue(person.getName().equals(person1));
    }

    @Then("^([^\"]*) is child of ([^\"]*)$")
    public void a_person_is_child_of_person(String person1, String person2) throws Throwable {
        Assert.assertTrue(FamilyTree.getInstance().isChildOf(person1, person2));
        Assert.assertTrue(FamilyTree.getInstance().isParentOf(person2, person1)); //Doppelt hält besser, oder so, aber ihr wolltet es ja in eurer Aufgabe so.
    }

    @Then("^([^\"]*) is daughter of ([^\"]*)$")
    public void a_person_is_daughter_of_another_person(String person1, String person2) throws Throwable {
        throw new PendingException();
    }

    @Then("^([^\"]*) is sibling of ([^\"]*)$")
    public void a_person_is_sibling_of_another_person(String person1, String person2) throws Throwable {
        throw new PendingException();
    }
    @Then("^([^\"]*) is cousin of ([^\"]*)$")
    public void a_person_is_cousin_of_another_person(String person1, String person2) throws Throwable {
        throw new PendingException();
    }
    @Then("^([^\"]*) is aunt of ([^\"]*)$")
    public void a_person_is_aunt_of_another_person(String person1, String person2) throws Throwable {
        throw new PendingException();
    }
    @Then("^([^\"]*) is uncle of ([^\"]*)$")
    public void a_person_is_uncle_of_another_person(String person1, String person2) throws Throwable {
        throw new PendingException();
    }
    @Then("^([^\"]*) is grandmother of ([^\"]*)$")
    public void a_person_is_grandmother_of_another_person(String person1, String person2) throws Throwable {
        throw new PendingException();
    }
    @Then("^([^\"]*) is grandfather of ([^\"]*)$")
    public void a_person_is_grandfather_of_another_person(String person1, String person2) throws Throwable {
        throw new PendingException();
    }
    @Then("^([^\"]*) is granddaughter of ([^\"]*)$")
    public void a_person_is_granddaugther_of_another_person(String person1, String person2) throws Throwable {
        throw new PendingException();
    }
    @Then("^([^\"]*) is grandson of ([^\"]*)$")
    public void a_person_is_grandson_of_another_person(String person1, String person2) throws Throwable {
        throw new PendingException();
    }

    @Then("^([^\"]*) is son of ([^\"]*)$")
    public void a_person_is_son_of_another_person(String person1, String person2) throws Throwable {
//        Person person = FamilyTree.getInstance().getSpouse(person2);
//        Assert.assertTrue(person.getName() == person1);
        throw new PendingException();
    }

//    @Then("^([^\"]*) is ([^\"]*) of ([^\"]*)$")
//    public void a_person_has_a_relationship_to_another_person(String person1, String relation, String person2) throws Throwable {
////        Person person = FamilyTree.getInstance().getSpouse(person2);
////        Assert.assertTrue(person.getName() == person1);
//        throw new PendingException();
//    }

    @After
    public void afterScenario() {
//        print FamilyTree.getInstance().print();
        FamilyTree.getInstance().reset();
    }
}
