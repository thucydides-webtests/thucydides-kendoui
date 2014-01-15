package net.thucydides.kendoui.jbehave.steps;

import net.thucydides.kendoui.jbehave.pages.GridPage;
import net.thucydides.kendoui.jbehave.pages.SelectionGridPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;

public class GridSteps {

    GridPage gridPage;
    SelectionGridPage selectionGridPage;

    @Given("I am on the kendoui grid page")
    public void givenIAmOnTheKendouiGridPage() {
        gridPage.open();
    }

    @Given("I am on the kendoui selectionable grid page")
    public void selectionableGridPage() {
        selectionGridPage.open();
    }

    Map<String, String> rowValues;

    @When("I read values from row $row")
    public void whenIReadValuesFromRow(int row) {
        rowValues = gridPage.getRow(row);
    }

    @When("I navigate to page $pageNumber")
    public void navigateToPage(int pageNumber){
        gridPage.navigateToPage(pageNumber);

    }

    @When("I select row $row")
    public void selectRow(int row) {
        selectionGridPage.selectRow(row);
    }

    @Then("the selected row should be $row")
    public void selectedRowShouldBe(int row) {
        selectionGridPage.getSelectedRows();
    }

    @Then("I should get the following cell values: $cellValues")
    public void thenIShouldGetTheFollowingCellValues(ExamplesTable cellValues) {
        Map<String, String> expectedRowValues = cellValues.getRows().get(0);
        assertThat(rowValues).isEqualTo(expectedRowValues);

    }
}
