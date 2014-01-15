package net.thucydides.kendoui.jbehave.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.kendoui.components.Grid;

import java.util.Map;

@DefaultUrl("http://demos.kendoui.com/web/grid/index.html")
public class GridPage extends PageObject {

    private Grid grid;

    public GridPage() {
        this.grid = Grid.withId("example").onPage(this);
    }

    public Map<String, String> getRow(int row) {
        return grid.getRow(row);
    }

    public void navigateToPage(int pageNumber) {
        grid.navigateToPage(pageNumber);
    }

    public void selectRow(int row) {
        grid.selectRow(row);
    }
}
