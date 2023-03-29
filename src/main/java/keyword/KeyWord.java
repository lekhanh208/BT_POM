package keyword;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static drivers.DriverManager.*;

public class KeyWord {

    private static int EXPLICIT_WAIT_TIMEOUT = 20;
    private static int WAIT_PAGE_LOADED_TIMEOUT = 30;

    public static void openUrl(String URL){
        getDriver().get(URL);
        waitForPageLoaded();
    }

    public static void scrollToEnd() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public static void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void sleep(long seconds){
        try {
            Thread.sleep((long) (1000*seconds));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebElement getElement(By by) {
        return getDriver().findElement(by);
    }

    public static void click(By by) {
        waitForElementClickable(by);
        getElement(by).click();
    }

    public static String getText(By by) {
        waitForElementVisible(by);
        return getElement(by).getText();
    }

    public static void inputText(By by, String text) {
        getElement(by).sendKeys(text);
    }
    public static void inputText(By by, String text, String ENTER) {
        getElement(by).sendKeys(text, Keys.ENTER);
    }

    public static void clearText(By by){
        getElement(by).clear();
    }

    public static String getAttribute(By by, String attributeName){
        waitForElementPresence(by);
        return getElement(by).getAttribute(attributeName);
    }

    public static boolean verifyElementVisible(By by){
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_PAGE_LOADED_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementPresence(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementClickable(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForTextToBePresentInElement(By by, String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }
}
