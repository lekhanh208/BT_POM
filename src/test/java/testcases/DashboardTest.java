package testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    @Test
    public void searchMenuSuccess(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("admin@example.com", "123456");
        dashboardPage.searchCorrectItemInMenu("Colors");
    }

    @Test
    public void searchMenuFail(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("admin@example.com", "123456");
        dashboardPage.searchWrongItemInMenu("Colors123");
    }

    @Test
    public void openAddProductPageOnMenu(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("admin@example.com", "123456");
        dashboardPage.openAddNewProductPage();
    }

    @Test
    public void openCategoryPageOnMenu(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.login("admin@example.com", "123456");
        dashboardPage.openCategoryPage();
    }

}
