package testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    @Test
    public void LoginSuccess(){
        loginPage = new LoginPage();
        loginPage.verifyHeaderPage();
        loginPage.login("admin@example.com", "123456");
    }

    @Test
    public void LoginFail(){
        loginPage = new LoginPage();
        loginPage.loginInvalid("admin@example.com123", "1234");
    }
}
