package net.thucydides.kendoui.jbehave.steps;

import net.thucydides.kendoui.jbehave.pages.NumericTextBoxPage;
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
public class NumericTextBoxSteps {

    NumericTextBoxPage numericTextBoxPage;

    @Given("I am on the kendoui numeric text box demo page")
    public void onNumericTextboxPage() {
        numericTextBoxPage.open();
    }

    @When("I enter '$value' into the price field")
    public void enterPrice(double value) {
        numericTextBoxPage.setPrice(value);

    }

    @Then("the price field should have a value of $price")
    public void priceShouldBe(double expected) {
        assertThat(numericTextBoxPage.getPrice()).isEqualTo(expected);
    }

    @When("I enter '$value' into the percentage field")
    public void enterPercentage(double value) {
        numericTextBoxPage.setPercentage(value);

    }

    @Then("the percentage field should have a value of $price")
    public void percentageShouldBe(double expected) {
        assertThat(numericTextBoxPage.getPercentage()).isEqualTo(expected);
    }
}
