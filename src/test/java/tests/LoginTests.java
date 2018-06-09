package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    //Test Data
    private String wrongUsername = "onur@swtestacademy.com";
    private String wrongPassword = "11122233444";
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod(description = "Method Level Setup!")
    public void methodLevelSetup() {
        //*************PAGE INSTANTIATIONS*************
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver, homePage);

        //*************PAGE METHODS********************
        //Open N11 HomePage and Go to Login Page
        loginPage.get();
    }

    @Test (priority = 2, description="Invalid Login Scenario with wrong username and password.")
    public void invalidLoginTest_InvalidUserNameInvalidPassword () {
        //*************PAGE METHODS********************
        loginPage.loginToN11(wrongUsername, wrongPassword);

        //*************ASSERTIONS***********************
        loginPage.verifyLoginPassword(("E-posta adresiniz veya şifreniz hatalı"));
    }

    @Test (priority = 1, description="Invalid Login Scenario with empty username and password.")
    public void invalidLoginTest_EmptyUserEmptyPassword ()  {
        //*************PAGE INSTANTIATIONS*************
        loginPage.loginToN11("","");

        //*************ASSERTIONS***********************
        loginPage.verifyLoginUserName("Lütfen e-posta adresinizi girin.");
        loginPage.verifyLoginPassword("Bu alanın doldurulması zorunludur.");
    }


}
