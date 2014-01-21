package net.thucydides.kendoui.jbehave.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.kendoui.components.NumericTextBox;

@DefaultUrl("http://demos.kendoui.com/web/numerictextbox/index.html")
public class NumericTextBoxPage extends PageObject {

    NumericTextBox price;
    NumericTextBox percentage;

    public NumericTextBoxPage() {
        price = NumericTextBox.withId("currency").onPage(this);
        percentage = NumericTextBox.withId("percentage").onPage(this);
    }

    public void setPrice(double priceValue) {
        price.setValue(priceValue);
    }

    public double getPrice() {
        return Double.parseDouble(price.getValue());
    }

    public String getDisplayedPrice() {
        return price.getDisplayedValue();
    }

    public void setPercentage(double priceValue) {
        percentage.setValue(priceValue);
    }

    public double getPercentage() {
        return Double.parseDouble(percentage.getValue());
    }
}
