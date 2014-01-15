package net.thucydides.kendoui.jbehave.steps;

import net.thucydides.kendoui.jbehave.pages.MultiSelectPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItems;


public class MultiSelectSteps {

    MultiSelectPage multiSelectPage;

    @Given("I am on the kendoui multiselect demo page")
    public void onMultiselectPage() {
        multiSelectPage.open();
    }

    @When("I select '$value' in the select list")
    public void selectMultiselectValue(String value) {
        multiSelectPage.select(value);
    }

    @Then("I should see the following multiselect values: $values")
    public void shouldSeeValues(List<String> values) {
        assertThat(multiSelectPage.getSelectedValues(), containsInAnyOrder(values.toArray()));
    }

}
