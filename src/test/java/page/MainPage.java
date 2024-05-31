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
        Coordinates coordinates = new Coordinates("300", "200");

        builder
                .moveByOffset(coordinates.getIntXCoordinate(), coordinates.getIntYCoordinate())
                .pause(Duration.ofSeconds(5))
                .contextClick()
                .pause(Duration.ofSeconds(5))
                .release()
                .perform();

        logger.info("Move to: " + coordinates);
        return this;
    }

    public Coordinates findExpectedCoordinates() {

        String coordinates = coordinatesElement.getText();

        String xCoordinate = coordinates.substring(0, coordinates.indexOf(".") - 1);
        String yCoordinate =
                coordinates.substring(coordinates.indexOf(" "), coordinates.lastIndexOf("."));

        Coordinates expectedCoordinates = new Coordinates(xCoordinate, yCoordinate);
        logger.debug("Expected:" + expectedCoordinates);
        return expectedCoordinates;
    }

    public Coordinates findActualCoordinates() {

        String actualCoordinate = actualCoordinates.getText();

        String actualXCoordinate = actualCoordinate.substring(0, actualCoordinate.indexOf(".") - 1);
        String actualYCoordinate =
                actualCoordinate.substring(
                        actualCoordinate.indexOf(" "), actualCoordinate.lastIndexOf("."));
        Coordinates actualCoordinates = new Coordinates(actualXCoordinate, actualYCoordinate);
        logger.debug("Actual:" + actualCoordinates);
        return actualCoordinates;
    }

    public MainPage clickOnCoordinate() {
        Actions builder = new Actions(webDriver);
        builder.click(coordinatesElement).pause(Duration.ofSeconds(3)).release().perform();
        logger.info("click on coordinate element");
        return this;
    }

    public boolean hintIsDisplayed() {
        return hint.isDisplayed();
    }

    public MainPage clickSearchButtonWithJs() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("arguments[0].click();", searchField);
        logger.info("click on search element");
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
        logger.info("past coordinates");
        return this;
    }

    public MainPage clickAndHold() {
        Actions builder = new Actions(webDriver);

        int xCoordinate = TestDataReader.getTestData("test.data.xOfSet");
        int yCoordinate = TestDataReader.getTestData("test.data.yOfSet");
        builder.moveToElement(canvas)
                .clickAndHold()
                .moveByOffset(xCoordinate,yCoordinate)
                .release()
                .perform();
        logger.info("Move by offset:" + xCoordinate+ ", " + yCoordinate);
        return this;
    }

    public MainPage reloadWithJs() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("location.reload()");
        logger.info("page reload");
        return this;
    }
}
