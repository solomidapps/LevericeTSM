package components.elements;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import utils.AllureUtils;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.AssertJUnit.assertEquals;

@Log4j2
public class MainMenuButton {
    private static final String MENU_BUTTON_XPATH = "//div[@class='button' and label[contains(text(),'%s')]]";
    private static final String MENU_BUTTON_DESCRIPTION_XPATH = "//div[@class='button']/..//label[contains(text(),'%s')]/..//span";

    public static void clickMenuButtonByText(String buttonText) {
        log.info("Clicking on menu button with text " + buttonText);
        try {
            $(By.xpath(String.format(MENU_BUTTON_XPATH, buttonText))).click();
        } catch (Exception e) {
            log.error("No such element to click");
            AllureUtils.takeScreenshot();
        }
    }

    public static void validateButtonDescription(String buttonText, String expectedDescription) {
        String actualButtonDescription = $(By.xpath(String.format(MENU_BUTTON_DESCRIPTION_XPATH, buttonText))).getText().trim();
        log.info("Validating button " + buttonText + " description. Found: " + actualButtonDescription + " Expected: "
                + expectedDescription);
        assertEquals("Description miss match", expectedDescription, actualButtonDescription);
    }
}
