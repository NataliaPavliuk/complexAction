package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import util.TestListener;
import webdriverManager.DriverSingleton;

@Listeners({TestListener.class})
public class CommonConditions {

    protected WebDriver webDriver;


    @BeforeMethod()
    public void setUp()
    {
        webDriver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser()
    {
        DriverSingleton.closeDriver();
    }
}
