package pages.category;

import helpers.ExcelHelper;
import org.openqa.selenium.By;
import org.testng.Assert;

import static keyword.KeyWord.*;

public class CategoryPage {

    private String URL = "https://cms.anhtester.com/admin/categories";
    private String PAGETEXT = "All categories";

    By headerPage = By.xpath("//h1");
    By buttonAddCategory = By.xpath("//a[@class='btn btn-primary']");
    By inputSearchCategory = By.xpath("//input[@id='search']");
    By tdCategoryName = By.xpath("//div[@class='card-body']//tr[1]//td[2]");

    public void verifyHeaderPage(){
        Assert.assertEquals(getText(headerPage), PAGETEXT, "FAIL.Header is not matching");
    }

    public CreateCategoryPage addNewCategory(){
        waitForPageLoaded();
        Assert.assertTrue(verifyElementVisible(buttonAddCategory), "Add New Category button is not visible");
        click(buttonAddCategory);
        return new CreateCategoryPage();
    }

    public void searchCategory(int rownumDataInExcel){
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("src/test/resources/datatest/CMS.xlsx", "AddNewCategory");
        String nameCategory = excel.getCellData("Name", rownumDataInExcel);
        inputText(inputSearchCategory, nameCategory, "ENTER");
        waitForElementVisible(tdCategoryName);
        Assert.assertEquals(getText(tdCategoryName), nameCategory, "The search name is not match with name on table");
    }
}
