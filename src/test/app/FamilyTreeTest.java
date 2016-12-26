package app;

//import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;

public class FamilyTreeTest {

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
        //bla bla
        throw new PendingException();
    }

    @Given("^I let ([^\"]*) and ([^\"]*) getting married$")
    public void i_let_romeo_and_juliette_getting_married(String person1, String person2) throws Throwable {
        FamilyTree.getInstance().marryPersons(person1, person2);
    }

    @Given("^I have a root ([^\"]*) at level (\\d+)$")
    public void i_have_an_endboss_in_my_family_tree(String rootPerson, int level) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^([^\"]*) is in my family tree$")
    public void a_person_is_in_my_family_tree(String name) throws Throwable {
        Person person = FamilyTree.getInstance().getPerson(name);
        Assert.assertTrue(person.getName() == name);
//        throw new PendingException();
    }

    @Then("^([^\"]*) is married to ([^\"]*)$")
    public void a_person_is_married_to_person(String person1, String person2) throws Throwable {
        Person person = FamilyTree.getInstance().getSpouse(person2);
        Assert.assertTrue(person.getName() == person1);
//        throw new PendingException();
    }

    @Then("^([^\"]*) is ([^\"]*) of ([^\"]*)$")
    public void a_person_has_a_relationship_to_another_person(String person1, String relation, String person2) throws Throwable {
        Person person = FamilyTree.getInstance().getSpouse(person2);
        Assert.assertTrue(person.getName() == person1);
//        throw new PendingException();
    }
    @Before
    public void beforeScenario() {
        FamilyTree.getInstance().reset();
    }
    @After
    public void afterScenario() {
        FamilyTree.getInstance().reset();
    }
}
