package pages.addNewTopic;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pages.base.BasePage;
import pages.createDirectChannelPage.CreateDirectChannel;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;
import static components.elements.Input.writeTextXpath;

@Log4j2
public class CreateTopicChannel extends BasePage {

    private static final String BUTTON_AddNewTopic_XPATH = "//div[contains(text(),'Add new Topic')]";
    private static final String AddChannelName_FIELD_XPATH = "//textarea[@placeholder='Add a channel name']";
    private static final String SEARCH_POSITION_FIELD_XPATH = "//div[text()='Creating Topic Channel']";
    private CreateDirectChannel channel = new CreateDirectChannel();
    private String userName = "";

    public CreateTopicChannel isPageOpened() {
        log.debug("Check the 'Creating Folder' pop up is displayed.");
        try {
            $(SEARCH_POSITION_FIELD_XPATH).shouldBe(Condition.visible);
        } catch (NoSuchElementException e) {
            log.error("'Creating Folder' pop up is not opened. Cannot find element Search Position Field");
            screenshot("create_folder_not_opened");
            Assert.fail("Impossible to create a folder: pop-up is not opened");
        }
        return this;
    }

    @Step("Add new Topic at Channel")
    public CreateTopicChannel addTopic (String channelName) {
        screenshot("before create Direct Channel");
        $(BUTTON_AddNewTopic_XPATH).click();
        channel.createDirectChannel(userName);
        writeTextXpath(AddChannelName_FIELD_XPATH,channelName);
        channel.tapButtonCreate();
        screenshot("after create Direct Channel");
        return this;
    }

}
