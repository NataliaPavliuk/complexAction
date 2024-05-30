package page;

import model.Coordinates;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import service.TestDataReader;

import java.time.Duration;

public class MainPage extends AbstractPage {

    @FindBy(xpath = "//div[contains(@class,'fontBodyLarge')]/div")
    private WebElement coordinatesElement;

    @FindBy(xpath = "//div[contains(@class,'fontBodyMedium')]//span/span")
    private WebElement actualCoordinates;

    @FindBy(xpath = "//*[text()='Copied to clipboard']")
    private WebElement hint;

    @FindBy(id = "searchboxinput")
    private WebElement searchField;

    @FindBy(xpath = "//canvas[contains(@class,'widget-scene-canvas')]")
    private WebElement canvas;

    private final String mainPageUrl= "https://www.google.com/maps/";

    private final Logger logger = LogManager.getRootLogger();

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.webDriver, this);
    }

    @Override
    public MainPage openPage() {
        webDriver.get(mainPageUrl);
        logger.info("The page is opened: " + mainPageUrl);
        return this;
    }

    public MainPage moveToCoordinates() {
        Actions builder = new Actions(webDriver);

        builder
                .moveByOffset(300, 200)
                .pause(Duration.ofSeconds(5))
                .contextClick()
                .pause(Duration.ofSeconds(5))
                .release()
                .perform();

        return this;
    }

    public Coordinates findExpectedCoordinates() {

        String coordinates = coordinatesElement.getText();

        String xCoordinate = coordinates.substring(0, coordinates.indexOf(".") - 1);
        String yCoordinate =
                coordinates.substring(coordinates.indexOf(" "), coordinates.lastIndexOf("."));

        return new Coordinates(xCoordinate, yCoordinate);
    }

    public Coordinates findActualCoordinates() {

        String actualCoordinate = actualCoordinates.getText();

        String actualXCoordinate = actualCoordinate.substring(0, actualCoordinate.indexOf(".") - 1);
        String actualYCoordinate =
                actualCoordinate.substring(
                        actualCoordinate.indexOf(" "), actualCoordinate.lastIndexOf("."));

        return new Coordinates(actualXCoordinate, actualYCoordinate);
    }

    public MainPage clickOnCoordinate() {
        Actions builder = new Actions(webDriver);
        builder.click(coordinatesElement).pause(Duration.ofSeconds(3)).release().perform();
        return this;
    }

    public boolean hintIsDisplayed() {
        return hint.isDisplayed();
    }

    public MainPage clickSearchButtonWithJs() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("arguments[0].click();", searchField);
        return this;
    }

    public MainPage pastCopiedCoordinates() {
        Actions builder = new Actions(webDriver);
        builder
                .click(searchField)
                .keyDown(Keys.CONTROL)
                .sendKeys("V")
                .pause(Duration.ofSeconds(3))
                .release()
                .perform();
        return this;
    }

    public MainPage clickAndHold() {
        Actions builder = new Actions(webDriver);

        builder.moveToElement(canvas)
                .clickAndHold()
                .moveByOffset(TestDataReader.getTestData("test.data.xOfSet"),TestDataReader.getTestData("test.data.yOfSet"))
                .release()
                .perform();

        return this;
    }

    public MainPage reloadWithJs() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("location.reload()");
        return this;
    }
}
