package net.thucydides.kendoui.jbehave.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

@DefaultUrl("http://demos.kendoui.com/web/datepicker/index.html")
public class DatePickerPage extends PageObject {

    private WebElementFacade datepicker;

    public void enterDate(String value) {
        datepicker.type(value);
    }

    public String getDate() {
        return datepicker.getValue();
    }
}
