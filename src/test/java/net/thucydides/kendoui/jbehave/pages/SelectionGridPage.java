package net.thucydides.kendoui.jbehave.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.kendoui.components.Grid;

import java.util.List;
import java.util.Map;

@DefaultUrl("http://demos.kendoui.com/web/grid/selection.html")
public class SelectionGridPage extends PageObject {

    private Grid grid;

    public SelectionGridPage() {
        this.grid = Grid.withId("rowSelection").onPage(this);
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

    public List<Integer> getSelectedRows() {
        return grid.getSelectedRows();
    }
}
