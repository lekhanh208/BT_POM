package pages;

import keyword.KeyWord;
import org.openqa.selenium.By;
import org.testng.Assert;

import static keyword.KeyWord.*;

public class LoginPage {
    private String URL = "https://cms.anhtester.com/login";
    private String PAGETEXT = "Login to your account.";

    By headerPage = By.xpath("//h1");
    By inputEmail = By.xpath("//input[@id='email']");
    By inputPassword = By.xpath("//input[@id='password']");
    By buttonLogin = By.xpath("//button[@type='submit']");
    By errorMessage = By.xpath("//div[@data-notify='container']");

    public void verifyHeaderPage(){
        Assert.assertEquals(getText(headerPage), PAGETEXT, "FAIL.Header is not matching");
    }

    public void enterEmail(String email){
        inputText(inputEmail, email);
    }

    public void enterPassword(String password){
        inputText(inputPassword, password);
    }

    public void clickLoginButton(){
        click(buttonLogin);
    }

    public DashboardPage login(String email, String password){
        openUrl(URL);
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        return new DashboardPage();
    }

    public void loginInvalid(String email, String password){
        openUrl(URL);
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        Assert.assertTrue(verifyElementVisible(errorMessage), "Error message is not display");
        System.out.println(getText(errorMessage));
    }
}
