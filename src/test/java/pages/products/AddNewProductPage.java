package pages.products;

import helpers.ExcelHelper;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.category.CategoryPage;

import static keyword.KeyWord.*;
import static keyword.KeyWord.click;

public class AddNewProductPage {
    private String URL = "https://cms.anhtester.com/admin/products/create";
    private String PAGETEXT = "Add New Product";

    By headerPage = By.xpath("//div[contains(@class,'titlebar')]//h5");
    By titleProductInformationForm = By.xpath("//h5[normalize-space()='Product Information']");
    By inputProductName = By.xpath("//input[@name='name']");
    By dropdownCategory = By.xpath("//button[@data-id='category_id']");
    By inputCategory = By.xpath("//input[@aria-controls='bs-select-1']");
    By dropdownBrand = By.xpath("//button[@data-id='brand_id']");
    By inputBrand = By.xpath("//input[@aria-controls='bs-select-2']");
    By inputUnit = By.xpath("//input[@name='unit']");
    By inputWeight = By.xpath("//input[@name='weight']");
    By inputMinQty = By.xpath("//input[@name='min_qty']");
    By tag = By.xpath("//span[@class='tagify__input']");

    By titlePriceStockForm = By.xpath("//h5[normalize-space()='Product price + stock']");
    By inputUnitPrice = By.xpath("//input[@name='unit_price']");
    By inputDiscount = By.xpath("//input[@name='discount']");
    By inputQuantity = By.xpath("//input[@name='current_stock']");
    By buttonSavePublish = By.xpath("//button[@value='publish']");

    public void verifyHeaderPage(){
        Assert.assertEquals(getText(headerPage), PAGETEXT, "FAIL.Header is not matching");
    }

    public InHouseProductsPage addDataNewProduct(int rownumDataInExcel){
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("src/test/resources/datatest/CMS.xlsx", "AddNewProduct");

        inputText(inputProductName,excel.getCellData("Product Name", rownumDataInExcel));
        click(dropdownCategory);
        inputText(inputCategory, excel.getCellData("Category", rownumDataInExcel), "ENTER");
        click(dropdownBrand);
        inputText(inputBrand, excel.getCellData("Brand", rownumDataInExcel), "ENTER");
        inputText(inputUnit, excel.getCellData("Unit", rownumDataInExcel));
        clearText(inputWeight);
        inputText(inputWeight, excel.getCellData("Weight", rownumDataInExcel));
        clearText(inputMinQty);
        inputText(inputMinQty, excel.getCellData("Minimum Purchase Qty", rownumDataInExcel));
        inputText(tag, excel.getCellData("Tags", rownumDataInExcel), "ENTER");
        sleep(3);
        inputText(tag, excel.getCellData("Tags", rownumDataInExcel + 1), "ENTER");
        sleep(3);
        inputText(tag, excel.getCellData("Tags", rownumDataInExcel + 2), "ENTER");

        scrollToElement(getElement(titlePriceStockForm));
        clearText(inputUnitPrice);
        inputText(inputUnitPrice, excel.getCellData("Unit price", rownumDataInExcel));
        clearText(inputDiscount);
        inputText(inputDiscount, excel.getCellData("Discount", rownumDataInExcel));
        clearText(inputQuantity);
        inputText(inputQuantity, excel.getCellData("Discount", rownumDataInExcel));

        scrollToEnd();
        click(buttonSavePublish);

        return new InHouseProductsPage() ;
    }

}
