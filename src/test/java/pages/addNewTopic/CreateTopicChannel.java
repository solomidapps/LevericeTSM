package pages.addNewTopic;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pages.base.BasePage;
import pages.createDirectChannelPage.CreateDirectChannel;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;
import static components.elements.Input.writeTextXpath;

@Log4j2
public class CreateTopicChannel{// extends BasePage {

    private static final String BUTTON_AddNewTopic_XPATH = "//div[contains(text(),'Add new Topic')]";
    private static final String AddChannelName_FIELD_XPATH = "//textarea[@placeholder='Add a channel name']";
    private static final String SEARCH_POSITION_FIELD_XPATH = "//div[text()='Creating Topic Channel']";
    private CreateDirectChannel channel = new CreateDirectChannel();
    private String userName = "TMS-Pre-Name TMS-Pre-Surname";

    public void isPageOpened() {
        log.debug("Check the 'Creating Folder' pop up is displayed.");
        try {
            $(By.xpath(SEARCH_POSITION_FIELD_XPATH)).shouldBe(Condition.visible);
        } catch (NoSuchElementException e) {
            log.error("'Creating Folder' pop up is not opened. Cannot find element Search Position Field");
            screenshot("create_folder_not_opened");
            Assert.fail("Impossible to create a folder: pop-up is not opened");
        }
    }

    public void isWorkSpacePageOpened() {
        log.debug("Check the Work Space is displayed.");
        try {
            $(By.xpath(BUTTON_AddNewTopic_XPATH)).shouldBe(Condition.visible);
        } catch (NoSuchElementException e) {
            log.error(" Work Space is not opened. Cannot find element Search Position Field");
            screenshot("work_space_not_opened");
            Assert.fail("Impossible to create a folder: Work Space is not opened");
        }
    }

    @Step("Add new Topic at Channel")
    public void addTopic (String topicName) {
        isWorkSpacePageOpened();
        screenshot("before create Topic at Channel");
        $(By.xpath(BUTTON_AddNewTopic_XPATH));
        channel.createDirectChannel(userName);
        writeTextXpath(AddChannelName_FIELD_XPATH,topicName);
        channel.tapButtonCreate();
        screenshot("after create Topic at Channel");
    }

}
