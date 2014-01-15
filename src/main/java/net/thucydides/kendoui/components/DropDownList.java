package net.thucydides.kendoui.components;


import ch.lambdaj.function.convert.PropertyExtractor;
import net.thucydides.core.annotations.findby.By;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;

import java.util.List;

import static ch.lambdaj.Lambda.convert;

public class DropDownList {

    private final String id;
    private final PageObject parentPage;

    private DropDownList(String id, PageObject parentPage) {
        this.id = id;
        this.parentPage = parentPage;
    }

    public static DropDownListBuilder withId(String id) {
        return new DropDownListBuilder(id);
    }

    public String getSelectedValue() {
        return dropdownList().findBy(".k-input").getText();
    }

    private WebElementFacade dropdownList() {
        return parentPage.findBy("//span[@aria-owns='" + id + "_listbox']");
    }

    private WebElement dropdownArrow() {
        return parentPage.getDriver().findElement(By.xpath("//span[@aria-owns='" + id + "_listbox']//span[@class='k-select']"));
    }

    public void selectValue(String value) {
        dropdownArrow().click();
        parentPage.waitFor(250).milliseconds();
        if (!parentPage.element(listboxItemSelector()).isCurrentlyVisible()) {
            dropdownArrow().click();
        }
        parentPage.waitForRenderedElements(dropdownItems());
        parentPage.getDriver().findElement(listItemWithValue(value)).click();
    }

    private String listboxItemSelector() {
        return "#" + id + "_listbox" + " li";
    }

    private org.openqa.selenium.By listItemWithValue(String value) {
        return By.xpath("//*[@id='" + id + "_listbox']//li[.='" + value + "']");
    }

    private org.openqa.selenium.By dropdownItems() {
        return By.cssSelector(listboxItemSelector());
    }

    public static class DropDownListBuilder {
        private final String id;

        private DropDownListBuilder(String id) {
            this.id = id;
        }

        public DropDownList onPage(PageObject page) {
            return new DropDownList(id, page);
        }
    }
}
