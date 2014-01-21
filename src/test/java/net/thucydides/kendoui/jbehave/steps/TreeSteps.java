package net.thucydides.kendoui.jbehave.steps;

import net.thucydides.kendoui.jbehave.pages.TreePage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * A description goes here.
 * User: john
 * Date: 15/01/2014
 * Time: 12:38 PM
 */
public class TreeSteps {

    TreePage treePage;

    @Given("I am on the kendoui tree page")
    public void onBasicTreePage() {
        treePage.open();
    }

    @When("I open the $entry entry")
    public void openEntry(String entry) {
        treePage.leftTree.openOrCloseEntry(entry);
    }

   @Then("the $entry entry should be expanded")
   public void shouldSeeExpanded(String entry) {
       assertTrue(treePage.leftTree.entryIsExpanded(entry));
   }

    @Then("I should see $expectedEntry")
    public void shouldSee(String expectedEntry) {
        assertTrue(treePage.leftTree.entryIsVisible(expectedEntry));
    }

    @When("I select the $entry entry")
    public void selectEntry(String entry) {
        treePage.leftTree.selectEntry(entry);
    }

    @When("I select the entry root")
    public void selectEntryRoot() {
        treePage.leftTree.selectRoot();
    }

    @Then("the $entry entry should be selected")
    public void shouldBeSelected(String entry) {
        assertTrue(treePage.leftTree.entryIsSelected(entry));
    }

    @When("I drag the $entry entry to $target")
    public void dragEntry(String entry, String target) {
        treePage.leftTree.dragEntry(entry).toUnderneath(target);
    }

    @When("I drag the $entry entry across the righthand tree to $target")
    public void dragEntryToRightHandTree(String entry, String target) {
        treePage.leftTree.dragEntry(entry).onto(treePage.rightTree).toUnderneath(target);
    }

    @Then("the $entry entry should appear underneath $parent")
    public void entryShouldAppearUnder(String entry, String parent) {
        List<String> children = treePage.leftTree.getChildrenOf(parent);
        assertThat(children).contains(entry);
    }

    @Then("the $entry entry should appear in the righthand tree underneath $parent")
    public void entryShouldAppearInRightHandTreeUnder(String entry, String parent) {
        List<String> children = treePage.rightTree.getChildrenOf(parent);
        assertThat(children).contains(entry);
    }

}
