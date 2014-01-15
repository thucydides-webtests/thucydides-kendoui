package net.thucydides.kendoui.jbehave.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import net.thucydides.kendoui.components.Tree;

@DefaultUrl("http://demos.kendoui.com/web/treeview/dragdrop.html")
public class TreePage extends PageObject {

    public Tree leftTree;
    public Tree rightTree;

    public TreePage() {
        this.leftTree = Tree.withId("treeview-left").onPage(this);
        this.rightTree = Tree.withId("treeview-right").onPage(this);
    }

}
