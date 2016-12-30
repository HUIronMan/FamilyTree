package app;

//import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import java.util.Set;



public class FamilyTreeTest {
    private Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        this.scenario = scenario;
//        scenario.write("This goes into the report(s)\n");
    }

    @Given("^I add a person named ([^\"]*) to the family tree who is ([^\"]*)$")
    public void i_add_a_person_to_my_family_tree(String name, String gender) throws Throwable {
       Gender gen = (gender.equals("female")) ? Gender.FEMALE : Gender.MALE;
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

    @Then("^([^\"]*) is in my family tree$")
    public void a_person_is_in_my_family_tree(String name) throws Throwable {
        Person person = FamilyTree.getInstance().getPerson(name);
        Assert.assertTrue(person.getName().equals(name));
    }

    @Then("^([^\"]*) is married to ([^\"]*)$")
    public void a_person_is_married_to_person(String person1, String person2) throws Throwable {
        Person person = FamilyTree.getInstance().getSpouse(person2);
        Assert.assertTrue(person.getName().equals(person1));
    }

    @Then("^([^\"]*) is child of ([^\"]*)$")
    public void a_person_is_child_of_person(String person1, String person2) throws Throwable {
        Assert.assertTrue(FamilyTree.getInstance().isChildOf(person1, person2));
        Assert.assertTrue(FamilyTree.getInstance().isParentOf(person2, person1)); //Doppelt hält besser, oder so, aber ihr wolltet es ja so in eurer Aufgabe.
    }

    @Then("^([^\"]*) is sibling of ([^\"]*)$")
    public void a_person_is_sibling_of_another_person(String person1, String person2) throws Throwable {
        Set<Person> parents1 = FamilyTree.getInstance().getParentsOf(person1);
        Set<Person> parents2 = FamilyTree.getInstance().getParentsOf(person2);
        Assert.assertTrue(parents1.equals(parents2));
        //Children who only had one parent before marriage automatically have two parents after a marriage
    }

    @Then("^([^\"]*) is cousin of ([^\"]*)$")
    public void a_person_is_cousin_of_another_person(String person1, String person2) throws Throwable {
        Assert.assertTrue(FamilyTree.getInstance().isCousinOf(person1, person2));
    }
    @Then("^([^\"]*) is mother's sibling of ([^\"]*)$")
    public void a_person_is_sibling_of_a_persons_mom(String person1, String person2) throws Throwable {
        Assert.assertTrue(FamilyTree.getInstance().isMothersSibling(person1, person2));
    }

    @Then("^([^\"]*) is father's sibling of ([^\"]*)$")
    public void a_person_is_sibling_of_a_persons_dad(String person1, String person2) throws Throwable {
        Assert.assertTrue(FamilyTree.getInstance().isFathersSibling(person1, person2));
    }

    @Then("^([^\"]*) is grandmother of ([^\"]*)$")
    public void a_person_is_grandmother_of_another_person(String person1, String person2) throws Throwable {
        Assert.assertTrue(FamilyTree.getInstance().isGrandparentOf(person1, person2));
    }

    @Then("^([^\"]*) is grandfather of ([^\"]*)$")
    public void a_person_is_grandfather_of_another_person(String person1, String person2) throws Throwable {
        Assert.assertTrue(FamilyTree.getInstance().isGrandparentOf(person1, person2));
    }

    @Then("^([^\"]*) is granddaughter of ([^\"]*)$")
    public void a_person_is_granddaugther_of_another_person(String person1, String person2) throws Throwable {
        Assert.assertTrue(FamilyTree.getInstance().isGrandchildOf(person1, person2));
    }

    @Then("^([^\"]*) is grandson of ([^\"]*)$")
    public void a_person_is_grandson_of_another_person(String person1, String person2) throws Throwable {
        Assert.assertTrue(FamilyTree.getInstance().isGrandchildOf(person1, person2));
    }
    @After
    public void afterScenario() {
        FamilyTree.getInstance().reset();
    }
}
