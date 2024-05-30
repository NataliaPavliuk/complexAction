package page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage
{
    protected WebDriver webDriver;

    protected abstract AbstractPage openPage();
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected AbstractPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }
}
