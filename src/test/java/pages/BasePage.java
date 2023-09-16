package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    private By inputUsername = By.name("username");
    private By inputPassword = By.name("password");
    private By btnLogin = By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input");
    private By btnLogOut = By.linkText("Log Out");

    public BasePage() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
    }

    public static void setupChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
    }

    public void url(String url) throws InterruptedException {
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(1000);
    }

    public void close() throws InterruptedException {
        Thread.sleep(1000);
        driver.close();
    }

    public WebElement findElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void clickear(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }

    public void sendText(String inputText, By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(inputText);
    }

    public void sendKey(CharSequence pressKeys, By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).sendKeys(pressKeys);
    }

    public String getText(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this.findElement(locator).getText();
    }
    public void escribirUsername(String username) {
        this.sendText(username, inputUsername);
    }

    public void escribirPassword(String password) {
        this.sendText(password, inputPassword);
    }
    public void login() {
        this.clickear(btnLogin);
    }
    public String getTextBtnLogOut() {
        return this.getText(btnLogOut);
    }
}
