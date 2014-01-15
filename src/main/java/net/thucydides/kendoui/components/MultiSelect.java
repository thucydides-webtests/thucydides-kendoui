package net.thucydides.kendoui.components;


import ch.lambdaj.function.convert.Converter;
import ch.lambdaj.function.convert.PropertyExtractor;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static ch.lambdaj.Lambda.convert;

public class MultiSelect {

    private final String id;
    private final PageObject parentPage;

    private MultiSelect(String id, PageObject parentPage) {
        this.id = id;
        this.parentPage = parentPage;
    }

    public static MultiSelectBuilder withId(String id) {
        return new MultiSelectBuilder(id);
    }

    public void selectByValue(String value) {
        WebElement multiselect = driver().findElement(multiselectElement());
        parentPage.withAction().moveToElement(multiselect).click().build().perform();
        parentPage.waitForRenderedElements(listItem(value));
        driver().findElement(listItem(value)).click();
    }

    public List<String> getSelectedOptionValues() {
        List<WebElement> selectedOptions = new Select(driver().findElement(By.id(id))).getAllSelectedOptions();
        return convert(selectedOptions, toValues());
    }

    private Converter<WebElement, String> toValues() {
        return new Converter<WebElement, String>() {
            @Override
            public String convert(WebElement from) {
                return from.getAttribute("value");
            }
        };
    }

    private WebDriver driver() {
        return parentPage.getDriver();
    }

    private By multiselectElement() {
        return By.xpath(String.format("//*[contains(@class,'k-multiselect')][select[@id='%s']]", id));
    }

    private By listItem(String value) {
        return By.xpath(String.format(".//ul[@id='%s_listbox']/li[.='%s']", id, value));
    }

    public static class MultiSelectBuilder {
        private final String id;

        private MultiSelectBuilder(String id) {
            this.id = id;
        }

        public MultiSelect onPage(PageObject page) {
            return new MultiSelect(id, page);
        }
    }
}
