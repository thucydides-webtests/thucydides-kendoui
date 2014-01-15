package net.thucydides.kendoui.jbehave.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.kendoui.components.MultiSelect;

import java.util.List;

@DefaultUrl("http://demos.kendoui.com/web/multiselect/index.html")
public class MultiSelectPage extends PageObject {

    MultiSelect multiSelect;

    public MultiSelectPage() {
        multiSelect = MultiSelect.withId("required").onPage(this);
    }

    public void select(String value) {
        multiSelect.selectByValue(value);
    }

    public List<String> getSelectedValues() {
        return multiSelect.getSelectedOptionValues();
    }

}
