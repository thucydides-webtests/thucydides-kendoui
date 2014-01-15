package net.thucydides.kendoui.components;


import ch.lambdaj.function.convert.PropertyExtractor;
import net.thucydides.core.annotations.findby.By;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;

import java.util.List;

import static ch.lambdaj.Lambda.convert;

public class AutoComplete {

    private final static int DEFAULT_TIMEOUT = 500;

    private final String id;
    private final PageObject parentPage;
    private final int timeout;

    private AutoComplete(String id, PageObject parentPage, int timeout) {
        this.id = id;
        this.parentPage = parentPage;
        this.timeout = timeout;
    }

    public static AutoCompleteBuilder withId(String id) {
        return new AutoCompleteBuilder(id);
    }

    public List<String> getSuggestions() {
        return convert(suggestionDropdownElements(), new PropertyExtractor("text"));
    }

    private List<WebElement> suggestionDropdownElements() {
        return parentPage.getDriver().findElements(By.cssSelector(suggestionDropdownSelector() + " li"));
    }

    private String suggestionDropdownSelector() {
        return "#" + id + "_listbox";
    }

    public void selectValue(String value) {
        enterText(value);
        parentPage.waitFor(timeout).milliseconds();
        parentPage.find(By.xpath("//ul[@id='" + id + "_listbox']/li[.='" + value + "']")).click();
    }

    public void enterText(String value) {
        inputField().sendKeys(value);
    }

    private WebElementFacade inputField() {
        return parentPage.find(By.id(id));
    }

    public String getValue() {
        return inputField().getValue();
    }

    public static class AutoCompleteBuilder {
        private final String id;
        private int timeout = DEFAULT_TIMEOUT;

        private AutoCompleteBuilder(String id) {
            this.id = id;
        }

        public AutoCompleteBuilder withTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public AutoComplete onPage(PageObject page) {
            return new AutoComplete(id, page, timeout);
        }
    }
}
