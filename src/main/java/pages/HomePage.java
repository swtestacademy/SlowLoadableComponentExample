package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertTrue;

public class HomePage extends SlowLoadableComponent<HomePage> {

    //*********Page Variables*********
    private String baseURL = "https://www.n11.com/";
    private WebDriver driver;
    private WebDriverWait wait;
    private BasePage basePage;

    //*********Constructor*********
    public HomePage (WebDriver driver) {
        super(new SystemClock(), 20);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        basePage = new BasePage(driver);
    }

    //*********Web Elements*********
    By signInButtonBy = By.className("btnSignIn");

    //*********Override LoadableComponent Methods*********
    //We need to go to the page at load method
    @Override
    protected void load() {
        this.driver.get(baseURL);
    }

    //We need to check that the page has been loaded.
    @Override
    protected void isLoaded() throws Error {
        try{
            driver.findElement(signInButtonBy);
        } catch(Exception e){
            System.out.println("Home page has not been loaded yet!");
            throw new Error(e.getMessage());
        }
    }

    //*********Page Methods*********
    //Go to LoginPage
    public LoginPage goToLoginPage (){
        basePage.click(signInButtonBy);
        return new LoginPage(this.driver, this);
    }

}
