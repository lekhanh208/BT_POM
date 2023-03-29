package testcases;

import common.BaseTest;
import helpers.ExcelHelper;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.category.CategoryPage;
import pages.category.CreateCategoryPage;

public class CategoryTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CategoryPage categoryPage;
    CreateCategoryPage createCategoryPage;

    @Test
    public void openCategoryPageOnMenu(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("admin@example.com", "123456");
        categoryPage = dashboardPage.openCategoryPage();
        categoryPage.verifyHeaderPage();
    }

    @Test
    public void clickOnAddNewCategoryButton(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("admin@example.com", "123456");

        categoryPage = dashboardPage.openCategoryPage();

        createCategoryPage = categoryPage.addNewCategory();
        createCategoryPage.verifyHeaderPage();
        createCategoryPage.addDataNewCategory(1);

        categoryPage.searchCategory(1);
    }
}
