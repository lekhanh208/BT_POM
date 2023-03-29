package testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.category.CategoryPage;
import pages.category.CreateCategoryPage;
import pages.products.AddNewProductPage;
import pages.products.InHouseProductsPage;

public class ProductTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AddNewProductPage addNewProductPage;
    InHouseProductsPage inHouseProductsPage;

    @Test
    public void testAddNewProduct(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("admin@example.com", "123456");

        addNewProductPage = dashboardPage.openAddNewProductPage();

        addNewProductPage.addDataNewProduct(1);
    }

    @Test
    public void testEditNewProduct(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("admin@example.com", "123456");

        addNewProductPage = dashboardPage.openAddNewProductPage();

        inHouseProductsPage = addNewProductPage.addDataNewProduct(1);
    }

}
