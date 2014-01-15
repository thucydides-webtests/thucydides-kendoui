package net.thucydides.kendoui.jbehave.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.kendoui.components.DropDownList;

@DefaultUrl("http://demos.kendoui.com/web/dropdownlist/index.html")
public class DropDownListPage extends PageObject {

    DropDownList colorDropdown;

    public DropDownListPage() {
        colorDropdown = DropDownList.withId("color").onPage(this);
    }

    public void selectColor(String color) {
        colorDropdown.selectValue(color);
    }

    public String getCapColor() {
        return $("#cap").getAttribute("class").replaceAll("-cap","");
    }

    public String getSelectedOption() {
        return colorDropdown.getSelectedValue();
    }
}
