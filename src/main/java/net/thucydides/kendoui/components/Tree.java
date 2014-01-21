package net.thucydides.kendoui.components;


import ch.lambdaj.function.convert.PropertyExtractor;
import com.beust.jcommander.internal.Lists;
import com.google.common.base.Optional;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.lang.annotation.Target;
import java.util.List;

import static ch.lambdaj.Lambda.convert;

public class Tree {

    private final String id;
    private final PageObject parentPage;

    private Tree(String id, PageObject parentPage) {
        this.id = id;
        this.parentPage = parentPage;
    }

    public static TreeBuilder withId(String id) {
        return new TreeBuilder(id);
    }

    public void openOrCloseEntry(String entry) {

        WebElement entryElement = parentPage.getDriver().findElement(byEntryName(entry));
        if (entryElement.isDisplayed()) {
            parentPage.withAction().moveToElement(entryElement).doubleClick().build().perform();
        }
    }

    public void selectEntry(String entry) {
        Optional<WebElement> entryElement = entryNamed(entry);
        if ((entryElement.isPresent()) && entryElement.get().isDisplayed()) {
            parentPage.withAction().moveToElement(entryElement.get()).click().build().perform();
        }
    }

    public boolean entryIsVisible(String entry) {
        Optional<WebElement> entryElement = entryNamed(entry);
        if (entryElement.isPresent()) {
            return entryElement.get().isDisplayed();
        }
        return false;
    }

    public Optional<WebElement> entryNamed(String entryName) {
        List<WebElement> entryElement = parentPage.getDriver().findElements(byEntryName(entryName));
        if (!entryElement.isEmpty()) {
            return Optional.of(entryElement.get(0));
        } else {
            return Optional.absent();
        }
    }

    public boolean entryIsExpanded(String entry) {
        WebElement icon = parentPage.find(byEntryIcon(entry));
        return icon.getAttribute("class").contains("k-minus");
    }

    public boolean entryIsSelected(String entry) {

        Optional<WebElement> entryElement = entryNamed(entry);
        if (entryElement.isPresent()) {
            return entryElement.get().getAttribute("class").contains("k-state-selected");
        }
        return false;
    }

    private By byEntryName(String entry) {
        return By.xpath("//*[@id='" + id + "']//span[.='" + entry + "']");
    }

    private By byEntryIcon(String entry) {
        return By.xpath("//*[@id='" + id + "']//div[span[.='" + entry + "']]/span[1]");
    }

    public By byChildrenOf(String entry) {
        return By.xpath("//*[@id='" + id + "']//li[div/span[.='"+ entry +"']]/ul/li");
    }

    public DragBuilder dragEntry(String entry) {
        return new DragBuilder(entry, this);
    }

    public List<String> getChildrenOf(String parent) {
        List<WebElementFacade> childElements = parentPage.findAll(byChildrenOf(parent));
        return convert(childElements, new PropertyExtractor("text"));
    }

    public void selectRoot() {
        String firstRootElement = parentPage.getDriver().findElement(By.cssSelector("#" + id + ".k-treeview>ul>li>div")).getText();
        selectEntry(firstRootElement);
    }

    public class DragBuilder {
        private final String entryToDrag;
        private Tree sourceTree;
        private Tree targetTree;

        public DragBuilder(String entryToDrag, Tree currentTree) {
            this.entryToDrag = entryToDrag;
            this.targetTree = currentTree;
            this.sourceTree = currentTree;
        }

        public DragBuilder onto(Tree targetTree) {
            this.targetTree = targetTree;
            return this;
        }

        public void toUnderneath(String target) {
            if (!targetTree.entryIsExpanded(target)) {
                targetTree.openOrCloseEntry(target);
            }
            WebElement sourceElement = sourceTree.entryNamed(entryToDrag).get();
            WebElement targetElement = targetTree.entryNamed(target).get();
            parentPage.withAction().click(sourceElement)
                                   .dragAndDrop(sourceElement, targetElement)
                                   .build().perform();
        }
    }

    public static class TreeBuilder {
        private final String id;

        private TreeBuilder(String id) {
            this.id = id;
        }

        public Tree onPage(PageObject page) {
            return new Tree(id, page);
        }
    }
}
