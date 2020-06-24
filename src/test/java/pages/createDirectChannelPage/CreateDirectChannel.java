package pages.createDirectChannelPage;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;
import static components.elements.Input.writeTextXpath;

@Log4j2
public class CreateDirectChannel extends BasePage {

    private static final String BUTTON_CreateNewDirectChannel_XPATH = "//div[contains(text(),'Create new Direct Channel')]";
    private static final String SEARCH_POSITION_FIELD_XPATH = "//div[text()='Creating Direct Channel'";
    private static final String REQUIRED_FIELD_XPATH = "//textarea[@placeholder='Required']";
    private static final String BUTTON_CREATE_XPATH = "//label[text()='Create']";

    public CreateDirectChannel isPageOpened() {
        log.debug("Check the 'Creating Folder' pop up is displayed.");
        try {
            $(By.xpath(SEARCH_POSITION_FIELD_XPATH)).shouldBe(Condition.visible);
        } catch (NoSuchElementException e) {
            log.error("'Creating Folder' pop up is not opened. Cannot find element Search Position Field");
            screenshot("create_folder_not_opened");
            Assert.fail("Impossible to create a folder: pop-up is not opened");
        }
        return this;
    }

    @Step("Create new Direct Channel")
    public void createDirectChannel (String userName) {
        screenshot("before create Direct Channel");
        $(By.xpath(BUTTON_CreateNewDirectChannel_XPATH)).click();
        isPageOpened();
        writeTextXpath(REQUIRED_FIELD_XPATH,userName);
        $(By.xpath(REQUIRED_FIELD_XPATH)).pressEnter();
        screenshot("after create Direct Channel");
        tapButtonCreate();
    }

    public void tapButtonCreate() {
        $(By.xpath(BUTTON_CREATE_XPATH)).click();
    }

}
