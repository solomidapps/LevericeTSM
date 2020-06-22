package pages.addFolderPage;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;

@Log4j2
public class SelectChannelLocationPage extends BasePage {
    private static final String SEARCH_LOCATION_FIELD_CSS = ".search";

    @Override
    public SelectChannelLocationPage isPageOpened() {
        log.debug("Check the 'Select Channel Location' pop up is displayed.");
        try {
            $(SEARCH_LOCATION_FIELD_CSS).shouldBe(Condition.visible);
        } catch (NoSuchElementException e) {
            log.error("'Select Channel Location' pop up is not opened. Cannot find element Search Location Field");
            screenshot("channel_location_not_opened");
            Assert.fail("Select Channel Location pop-up cannot be opened");
        }
        return this;
    }
}
