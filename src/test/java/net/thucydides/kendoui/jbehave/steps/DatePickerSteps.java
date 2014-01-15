package net.thucydides.kendoui.jbehave.steps;

import net.thucydides.kendoui.jbehave.pages.DatePickerPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.fest.assertions.Assertions.assertThat;

/**
 * A description goes here.
 * User: john
 * Date: 9/01/2014
 * Time: 2:01 PM
 */
public class DatePickerSteps {

    DatePickerPage datePickerPage;

    @Given("I am on the kendoui datepicker demo page")
    public void onDatepickerPage() {
        datePickerPage.open();
    }

    @When("I enter '$value' into the date field")
    public void enterDate(String value) {
        datePickerPage.enterDate(value);

    }
    @Then("the date field should have a value of '$expected'")
    public void dateShouldBe(String expected) {
        assertThat(datePickerPage.getDate()).isEqualTo(expected);
    }
}
