package tests;

import model.Coordinates;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import page.MainPage;

public class ComplexActionsTest extends CommonConditions{

  @Test
  public void copyAndPasteCoordinates() {

    MainPage mainPage = new MainPage(webDriver);

    mainPage.openPage()
            .moveToCoordinates();

    Coordinates expectedCoordinates = mainPage.findExpectedCoordinates();

    mainPage.clickOnCoordinate();

    Assertion assertion = new Assertion();
    assertion.assertTrue(mainPage.hintIsDisplayed());

    mainPage.clickSearchButtonWithJs()
            .pastCopiedCoordinates();

    assertion.assertEquals(expectedCoordinates, mainPage.findActualCoordinates());
  }
}
