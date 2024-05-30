package tests;

import org.testng.annotations.Test;
import page.MainPage;

public class MoveTest extends CommonConditions{

    @Test
    public void clickAndHoldAction() {

        MainPage mainPage = new MainPage(webDriver);
        
        mainPage.openPage()
                .clickAndHold()
                .reloadWithJs();
    }
}
