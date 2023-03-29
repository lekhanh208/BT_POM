package pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import pages.products.AddNewProductPage;
import pages.category.CategoryPage;
import pages.products.AllProductsPage;

import static keyword.KeyWord.*;

public class DashboardPage {
    private String URL = "https://cms.anhtester.com/admin";

    By inputSearchMenu = By.xpath("//input[@id='menu-search']");
    By firstItemAfterSearch = By.xpath("//ul[@id='search-menu']//span");

    public void openItemInMenu(String itemName){
        By menuItem = By.xpath("//span[.='"+ itemName +"']");
        click(menuItem);
    }
    public void searchCorrectItemInMenu(String itemName){
        inputText(inputSearchMenu, itemName);
        Assert.assertTrue(verifyElementVisible(firstItemAfterSearch), "Item is not displayed after searching");
        Assert.assertEquals(getText(firstItemAfterSearch), itemName, "Item is not matching with search name");
    }

    public void searchWrongItemInMenu(String itemName){
        inputText(inputSearchMenu, itemName);
        Assert.assertTrue(verifyElementVisible(firstItemAfterSearch), "Item is not displayed after searching");
        Assert.assertEquals(getText(firstItemAfterSearch), "Nothing found", "The item are still displayed");
    }

    public AddNewProductPage openAddNewProductPage(){
        waitForPageLoaded();
        openItemInMenu("Products");
        openItemInMenu("Add New Product");
        return new AddNewProductPage();
    }

    public AllProductsPage openAllProductsPage(){
        waitForPageLoaded();
        openItemInMenu("Products");
        openItemInMenu("All products");
        return new AllProductsPage();
    }

    public CategoryPage openCategoryPage(){
        waitForPageLoaded();
        openItemInMenu("Products");
        openItemInMenu("Category");
        return new CategoryPage();
    }

}
