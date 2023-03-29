package pages.category;

import helpers.ExcelHelper;
import org.openqa.selenium.By;
import org.testng.Assert;

import static keyword.KeyWord.*;

public class CreateCategoryPage extends CategoryPage{
    private String URL = "https://cms.anhtester.com/admin/categories/create";
    private String PAGETEXT = "Category Information";

    By headerPage = By.xpath("//h5");
    By inputName = By.xpath("//input[@id='name']");
    By dropdownParentCategory = By.xpath("//button[@title='No Parent']");
    By inputParentCategory = By.xpath("//input[@aria-controls='bs-select-1']");
    By inputOrderingNumber = By.xpath("//input[@id='order_level']");
    By dropdownType = By.xpath("//button[@title='Physical']");
    By browserBanner = By.xpath("(//div[.='Browse'])[1]");
    By browserIcon = By.xpath("(//div[.='Browse'])[2]");
    By searchImage = By.xpath("//input[@name='aiz-uploader-search']");
    By selectedImage = By.xpath("(//div[@class='card card-file aiz-uploader-select'])[1]");
    By buttonAddFiles = By.xpath("//button[contains(.,'Add Files')]");
    By inputMetaTitle = By.xpath("//input[@name='meta_title']");
    By buttonSave = By.xpath("//button[@type='submit']");

    public void verifyHeaderPage(){
        Assert.assertEquals(getText(headerPage), PAGETEXT, "FAIL.Header is not matching");
    }

    public void selectOptionInDropdown(By dropdown, String option){
        click(dropdown);
        click(By.xpath("//span[text()='" + option + "']"));
    }

    public void selectImage(By buttonBrowser, String nameFile){
        click(buttonBrowser);
        inputText(searchImage, nameFile, "ENTER");
        sleep(5);
        click(selectedImage);
        click(buttonAddFiles);
    }

    public CategoryPage addDataNewCategory(int rownumDataInExcel){
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("src/test/resources/datatest/CMS.xlsx", "AddNewCategory");
        inputText(inputName,excel.getCellData("Name", rownumDataInExcel));
        click(dropdownParentCategory);
        inputText(inputParentCategory, excel.getCellData("Parent Category", rownumDataInExcel), "ENTER");
        inputText(inputOrderingNumber, excel.getCellData("Ordering Number", rownumDataInExcel));
        selectOptionInDropdown(dropdownType, excel.getCellData("Type", rownumDataInExcel));
        selectImage(browserBanner, excel.getCellData("Banner", rownumDataInExcel));
        scrollToEnd();
        selectImage(browserIcon, excel.getCellData("Icon", rownumDataInExcel));
        scrollToEnd();
        inputText(inputMetaTitle, excel.getCellData("Meta Title", rownumDataInExcel));
        click(buttonSave);
        return new CategoryPage();
    }
}
