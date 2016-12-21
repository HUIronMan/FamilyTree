package test.java;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class FamilyTreeTest {
    @Given("^I have (\\d+) people in my family tree$")
    public void i_have_startAmount_people_in_my_family_tree(int amount) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I add a person named ([^\"]*) to the family tree at level (\\d+) who is ([^\"]*)$")
    public void i_add_a_person_named_name_to_the_family_tree_at_level_level(String name, int level, String gender ) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
    @Given("^I connect ([^\"]*) as a child to ([^\"]*)")
    public void i_connect_child_as_a_child_to_parent(String child, String parent) throws Throwable {
        //bla bla
        throw new PendingException();
    }

    @Given("^I let ([^\"]*) and ([^\"]*) getting married$")
    public void i_let_person_and_person_getting_married(String person1, String person2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^([^\"]*) and ([^\"]*) should be married$")
    public void person_and_person_should_be_married(String person1, String person2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();
    }

    @Given("^I have a root ([^\"]*) at level (\\d+)$")
    public void i_have_a_root_person_at_level(int arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

//    @Given("^I connect ([^\"]*) as parent with ([^\"]*)$")
//    public void i_connect_person_as_parent_with_person(int arg1, int arg2) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }

    @Then("^([^\"]*) is parent of ([^\"]*)")
    public void person_is_parent_of_person(int arg1, int arg2, DataTable arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();
    }

    @Then("^Then ([^\"]*) is <child> of ([^\"]*)$")
    public void then_person_is_child_of_person_and_person(String child, String parent)  throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();
    }

    @Then("^Then <person(\\d+)> and <person(\\d+)> are siblings$")
    public void then_person_and_person_are_siblings(int arg1, int arg2, DataTable arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        throw new PendingException();
    }
}
