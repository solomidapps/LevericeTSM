package pages.addFolderPage;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;

@Log4j2
public class ChannelMembersPage extends BasePage {

    private static final String SEARCH_MEMBER_FIELD_CSS = ".search-input";

    @Override
    public ChannelMembersPage isPageOpened() {
        log.debug("Check the 'Channel Members' pop up is displayed.");
        try {
            $(SEARCH_MEMBER_FIELD_CSS).shouldBe(Condition.visible);
        } catch (NoSuchElementException e) {
            log.error("'Channel Members' pop up is not opened. Cannot find element Search Member Field");
            screenshot("channel_members_not_opened");
            Assert.fail("Channel Members pop-up cannot be opened");
        }
        return this;
    }
}
