package net.thucydides.kendoui.components;


import ch.lambdaj.function.convert.Converter;
import ch.lambdaj.function.convert.PropertyExtractor;
import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static ch.lambdaj.Lambda.convert;
import static ch.lambdaj.Lambda.extract;

public class Grid {

    private final String id;
    private final PageObject parentPage;

    private Grid(String id, PageObject parentPage) {
        this.id = id;
        this.parentPage = parentPage;
    }

    public static GridBuilder withId(String id) {
        return new GridBuilder(id);
    }

    public Map<String, String> getRow(int row) {
        List<String> headers = getHeaders();
        List<WebElementFacade> cells = parentPage.findAll(cellsInnthRow(row));

        Map<String, String> cellValues = Maps.newHashMap();
        int column = 0;
        for(String header : headers) {
            cellValues.put(header, cells.get(column++).getText());
        }
        return cellValues;
    }

    public List<String> getHeaders() {
       return convert(parentPage.findAll(By.cssSelector("#" + id + " thead th")), new PropertyExtractor("text"));
    }

    private By rowElements() {
        return By.cssSelector("#" + id + " tbody tr");
    }

    private By nthRowElement(int row) {
        return By.cssSelector("#" + id + " tbody tr:nth-child(" + row + ")");
    }

    private By cellsInnthRow(int row) {
        return By.cssSelector("#" + id + " tbody tr:nth-child(" + row + ") td");
    }

    public void navigateToPage(int pageNumber) {
        parentPage.findBy("#grid .k-pager-numbers").find(By.linkText(Integer.toString(pageNumber))).click();
    }

    public void selectRow(int row) {
        parentPage.findBy("#" + id + " tbody tr:nth-child(" + row+ ")").click();
    }

    public List<Integer> getSelectedRows() {
        List<WebElementFacade> rows = parentPage.findAll("#" + id + " tbody tr");
        List<String> selectedRowIds = convert(parentPage.findAll("#" + id + " tbody tr.k-state-selected"), toDataUids());
        List<Integer> selectedRowIndexes = Lists.newArrayList();
        int index = 1;
        for(WebElementFacade row : rows) {
            if (selectedRowIds.contains(row.getAttribute("data-uid"))) {
                selectedRowIndexes.add(index);
            }
            index++;
        }
        return selectedRowIndexes;
    }

    private Converter<WebElementFacade, String> toDataUids() {
        return new Converter<WebElementFacade, String>() {

            @Override
            public String convert(WebElementFacade from) {
                return from.getAttribute("data-uid");
            }
        };
    }


    public static class GridBuilder {
        private final String id;

        private GridBuilder(String id) {
            this.id = id;
        }

        public Grid onPage(PageObject page) {
            return new Grid(id, page);
        }
    }
}
