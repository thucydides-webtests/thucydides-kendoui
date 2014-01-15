package net.thucydides.kendoui.jbehave.steps;

import com.beust.jcommander.internal.Lists;
import net.thucydides.kendoui.jbehave.pages.AutoCompletePage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;


public class AutocompleteSteps {

    AutoCompletePage autoCompletePage;

    @Given("I am on the kendoui autocomplete demo page")
    public void givenIAmOnTheKendouiAutocompleteDemoPage() {
        autoCompletePage.open();
    }

    @When("I enter '$value' into the country field")
    public void whenIEnterIntoTheCountryField(String value) {
        autoCompletePage.enterCountry(value);
    }

    @Then("I should see the following values: $expectedValues")
    public void thenIShouldSeeTheFollowingValues(ExamplesTable expectedValues) {
        List<String> expectedCountries = getValuesFromTable("country", expectedValues.getRows());
        List<String> displayedCountries = autoCompletePage.getSuggestedCountries();
        assertThat(displayedCountries).isEqualTo(expectedCountries);
    }

    @When("I select '$value' from the suggestions")
    public void selectValue(String value) {
        autoCompletePage.selectCountry(value);
    }

    @Then("the displayed value should be '$value'")
    public void shouldDisplay(String value) {
        autoCompletePage.getSelectedCountries();
    }


    private List<String> getValuesFromTable(String column, List<Map<String, String>> rows) {
        List<String> values = Lists.newArrayList();
        for(Map<String, String> row : rows) {
            values.add(row.get(column));
        }
        return values;
    }
}
