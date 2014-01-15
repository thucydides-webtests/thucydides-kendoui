package net.thucydides.kendoui.jbehave.steps;

import net.thucydides.kendoui.jbehave.pages.DropDownListPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.fest.assertions.Assertions.assertThat;

public class DropdownSteps {


    DropDownListPage dropDownListPage;

    @Given("I am on the kendoui dropdownlist demo page")
    public void onDropdownPage() {
        dropDownListPage.open();

    }

    @When("I select '$color' into the cap color list")
    public void selectsColor(String color) {
        dropDownListPage.selectColor(color);

    }
    @Then("I should see the $color cap")
    public void shouldSeeCapOfColor(String color){
        assertThat(dropDownListPage.getCapColor()).isEqualTo(color);
    }

    @Then("the selected option in the dropdown list should be '$value'")
    public void selectedValueShouldBe(String value) {
        assertThat(dropDownListPage.getSelectedOption()).isEqualTo(value);
    }
}
