package net.thucydides.kendoui.jbehave.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.kendoui.components.AutoComplete;

import java.util.List;

@DefaultUrl("http://demos.kendoui.com/web/autocomplete/index.html")
public class AutoCompletePage extends PageObject {

    AutoComplete autoCompleteField;

    public AutoCompletePage() {
        autoCompleteField = AutoComplete.withId("countries").withTimeout(250).onPage(this);
    }

    public void enterCountry(String value) {
        autoCompleteField.enterText(value);
    }

    public List<String> getSuggestedCountries() {
        return autoCompleteField.getSuggestions();
    }

    public void selectCountry(String value) {
        autoCompleteField.selectValue(value);
    }

    public String getSelectedCountries() {
        return autoCompleteField.getValue();
    }
}
