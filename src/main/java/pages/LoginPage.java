package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertTrue;

public class LoginPage extends SlowLoadableComponent<LoginPage> {

    //*********Page Variables*********
    private WebDriver driver;
    private WebDriverWait wait;
    private BasePage page;
    private LoadableComponent<HomePage> parent;
    private final String loginURL = "https://www.n11.com/giris-yap";

    //*********Constructor*********
    public LoginPage (WebDriver driver, LoadableComponent<HomePage> parent) {
         super(new SystemClock(), 20);
         this.driver = driver;
         this.wait = new WebDriverWait(driver,10);
         page = new BasePage(this.driver);
         this.parent = parent;
    }

    //*********Web Elements*********
    By usernameBy = By.id("email");
    By passwordBy = By.id("password");
    By loginButtonBy = By.id("loginButton");
    By errorMessageUsernameBy = By.cssSelector("#loginForm .error:nth-of-type(1) .errorMessage");
    By errorMessagePasswordBy = By.cssSelector("#loginForm .error:nth-of-type(2) .errorText");

    //*********Override LoadableComponent Methods*********
    //We need to go to the page at load method
    @Override
    protected void load() {
        parent.get().goToLoginPage();
    }

    //We need to check that the page has been loaded.
    @Override
    protected void isLoaded() throws Error {
        try{
            driver.findElement(usernameBy);
            driver.findElement(passwordBy);
            driver.findElement(loginButtonBy);
        } catch(Exception e){
            System.out.println("Login page has not been loaded yet!");
            throw new Error(e.getMessage());
        }
    }

    //*********Page Methods*********
    public void loginToN11 (String username, String password){
        //Enter Username(Email)
        page.writeText(usernameBy,username);
        //Enter Password
        page.writeText(passwordBy, password);
        //Click Login Button
        page.click(usernameBy); //In order to click right, this line needed. Site related.
        page.click(loginButtonBy);
    }

    //Verify Username Condition
    public void verifyLoginUserName (String expectedText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageUsernameBy));
        Assert.assertEquals(page.readText(errorMessageUsernameBy), expectedText);
    }

    //Verify Password Condition
    public void verifyLoginPassword (String expectedText) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessagePasswordBy));
        Assert.assertEquals(page.readText(errorMessagePasswordBy), expectedText);
    }
}
