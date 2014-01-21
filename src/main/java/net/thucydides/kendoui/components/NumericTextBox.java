package net.thucydides.kendoui.components;


import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class NumericTextBox {

    private final String id;
    private final PageObject parentPage;
    private final boolean isTabRequired;

    private NumericTextBox(String id, PageObject parentPage) {
        this(id, parentPage, false);
    }

    private NumericTextBox(String id, PageObject parentPage, boolean isTabRequired) {
        this.id = id;
        this.parentPage = parentPage;
        this.isTabRequired = isTabRequired;
    }

    public static NumericTextBoxBuilder withId(String id) {
        return new NumericTextBoxBuilder(id);
    }

    public void setValue(double value) {
        parentPage.withAction().moveToElement(formattedField()).build().perform();

        WebElement upArrow = parentPage.getDriver().findElement(upArrow());
        WebElement downArrow = parentPage.getDriver().findElement(downArrow());

        upArrow.click();
        downArrow.click();

        inputField().clear();
        inputField().sendKeys(Double.toString(value));
        blurInputField();
    }

    private void blurInputField() {
        if (isTabRequired) {
            inputField().sendKeys(Keys.TAB);
        }
        parentPage.evaluateJavascript("$('#" + id + "').blur()");
    }

    private WebElement formattedField() {
        return parentPage.getDriver().findElement(formattedValueSelector());
    }

    private WebElement inputField() {
        return parentPage.getDriver().findElement(inputFieldSelector());
    }

    public String getValue() {
        return inputField().getAttribute("value");
    }

    public String getDisplayedValue() {
        return formattedField().getAttribute("value");
    }

    private By formattedValueSelector() {
        return By.xpath(String.format("//span[input[@id='%s']]//input[contains(@class,'k-formatted-value')]",id));
    }

    private By inputFieldSelector() {
        return By.id(id);
    }

    private By upArrow() {
        return By.xpath(String.format("//span[input[@id='%s']]//span[contains(@class,'k-i-arrow-n')]",id));
    }

    private By downArrow() {
        return By.xpath(String.format("//span[input[@id='%s']]//span[contains(@class,'k-i-arrow-s')]",id));
    }

    public static class NumericTextBoxBuilder {
        private final String id;
        private final boolean requiresTab;

        private NumericTextBoxBuilder(String id) {
            this.id = id;
            this.requiresTab = false;
        }

        private NumericTextBoxBuilder(String id, boolean requiresTab) {
            this.id = id;
            this.requiresTab = requiresTab;
        }


        public NumericTextBoxBuilder requiresTab() {
            return new NumericTextBoxBuilder(id, true);
        }

        public NumericTextBox onPage(PageObject page) {
            return new NumericTextBox(id, page, requiresTab);
        }
    }
}
