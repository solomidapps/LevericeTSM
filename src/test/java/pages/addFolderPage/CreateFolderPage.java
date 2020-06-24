package pages.addFolderPage;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Folder;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pages.base.BasePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;
import static components.elements.Input.writeTextCss;
import static components.elements.Input.writeTextXpath;


@Log4j2
public class CreateFolderPage extends BasePage {
    //Controls available within the page
    private static final String SEARCH_POSITION_FIELD_CSS = ".smart-bar-text.last-part-input.with-popup-icon";
    private static final String CLEAR_BUTTON_CSS = ".smartbar-clear-input";
    private static final String INVALID_PATH_MESSAGE_XPATH = "//div[@class = 'snackbar']/div";
    private static final String GO_TO_SELECT_CHANNEL_BUTTON_CSS = ".smartbar-popup";
    private static final String FOLDER_NAME_FIELD_XPATH = "//textarea[@placeholder= 'Add a folder name']";
    private static final String NAME_ERROR_MESSAGE_CSS = ".error-message";
    private static final String FOLDER_MEMBERS_CSS = ".source";
    private static final String MAKE_PRIVATE_TOGGLE_XPATH = "//*[@class = 'modal-window-content__wrapper']//*[@class = 'toggle-wrapper']";
    private static final String PRIVATE_TOGGLE_ON_CSS = ".toggle on";
    private static final String OPTIONS_BUTTON_CSS = ".option-button";
    private static final String FOLDER_DESCRIPTION_FIELD_XPATH = "//textarea[@placeholder= 'Add a folder description. (Optional)']";
    private static final String SORT_SUBCHANNELS_DROPDOWN_CSS = ".dropdown-list default";
    private static final String SORT_SUBCHANNELS_DROPDOWN_ITEM_XPATH = "//*[@class = 'dropdown-list-item selected-item' or @class = 'dropdown-list-item']//span[contains(text(), '%s')]";
    private static final String CANCEL_BUTTON_CSS = ".button-cancel.flex.middle.center";
    private static final String CREATE_BUTTON_CSS = ".button-accept.button-accept";
    private static final String CLOSE_BUTTON_CSS = ".close-window-icon";

    //Error Messages that can be received
    private static final String errorUnique = "The entered name must be unique within one parent.";
    private static final String errorLength = "Invalid length of name. It can be up to 50 characters and can't be empty.";
    private static final String errorPath = "Please specify a different path to create the channel.";

    @Step("Verifying Creating Folder pop-up is opened ")
    public CreateFolderPage isPageOpened() {
        log.debug("Check the 'Creating Folder' pop up is displayed.");
        try {
            $(SEARCH_POSITION_FIELD_CSS).shouldBe(Condition.visible);
        } catch (NoSuchElementException e) {
            log.error("'Creating Folder' pop up is not opened. Cannot find element Search Position Field");
            screenshot("create_folder_not_opened");
            Assert.fail("Impossible to create a folder: pop-up is not opened");
        }
        return this;
    }

    @Step("Verifying Options section in the 'Creating Folder' pop up is opened ")
    public CreateFolderPage isOptionSectionOpened() {
        log.info("Open Options section in the 'Creating Folder' pop up.");
        log.debug("Check the Options section is displayed.");
        try {
            $(SORT_SUBCHANNELS_DROPDOWN_CSS).shouldBe(Condition.visible);
        } catch (NoSuchElementException e) {
            log.error("Options section was not opened. Cannot find element Sort subchannels dropdown");
            screenshot("option_section_not_opened");
            Assert.fail("Impossible to proceed: options section is not available");
        }
        return this;
    }

    @Step("Filling in 'Search Position' field with '{text}'")
    public CreateFolderPage fillInPosition(String text) {
        writeTextCss(SEARCH_POSITION_FIELD_CSS, text);
        return this;
    }

    @Step("Get text from 'Search Position' field")
    public String getPositionText() {
        return $(SEARCH_POSITION_FIELD_CSS).getText();
    }

    @Step("Clearing 'Search Position' field")
    public CreateFolderPage clearPosition() {
        log.debug("Clear 'Position' field via [x] button");
        $(CLEAR_BUTTON_CSS).click();
        return this;
    }

    @Step("Filling in 'Folder Name' field with '{text}'")
    public CreateFolderPage fillInFolderName(String text) {
        writeTextXpath(FOLDER_NAME_FIELD_XPATH, text);
        return this;
    }

    @Step("Switch Make Folder Private toggle to '{isPrivate}'")
    public CreateFolderPage switchPrivacy(boolean isPrivate) {
        log.debug("Set 'Make Folder Private' to " + isPrivate);
        boolean currentState = $(PRIVATE_TOGGLE_ON_CSS).isDisplayed();
        if (currentState && !isPrivate) {
            $(By.xpath(MAKE_PRIVATE_TOGGLE_XPATH)).click();
        }
        return this;
    }

    @Step("Click 'Options' button to open the section")
    public CreateFolderPage openOptionsSection() {
        $(OPTIONS_BUTTON_CSS).click();
        isOptionSectionOpened();
        return this;
    }

    @Step("Filling in 'Folder Description' field with '{text}'")
    public CreateFolderPage fillInDescription(String text) {
        writeTextXpath(FOLDER_DESCRIPTION_FIELD_XPATH, text);
        return this;
    }

    @Step("Selecting '{option}' option in 'Sort Subchannels' dropdown ")
    public CreateFolderPage selectSubchannelsSorting(String option) {
        log.info("Open 'Sort Subchannels' dropdown");
        $(SORT_SUBCHANNELS_DROPDOWN_CSS).click();
        log.debug("Select " + option + " in 'Sort Subchannels' dropdown");
        String THIS_OPTION_XPATH = String.format(SORT_SUBCHANNELS_DROPDOWN_ITEM_XPATH, option);
        try {
            $(By.xpath(THIS_OPTION_XPATH)).click();
        } catch (NoSuchElementException e) {
            log.error("There is no such option in the dropdown");
            Assert.fail("Impossible to proceed: required option is not present in the dropdown");
        }
        return this;
    }

    @Step("Click on [Go to Select Channel Location] button")
    public SelectChannelLocationPage goToSelectPositionMenu() {
        log.debug("Open Select Channel Location Page from Creating Folder pop-up");
        $(GO_TO_SELECT_CHANNEL_BUTTON_CSS).click();
        SelectChannelLocationPage selectChannelLocationPage = new SelectChannelLocationPage();
        selectChannelLocationPage.isPageOpened();
        return selectChannelLocationPage;
    }

    @Step("Click on [Go to Select Members Location] button")
    public ChannelMembersPage goToSelectChannelMembersMenu() {
        log.debug("Open Select Channel Members Page from Creating Folder pop-up");
        $(FOLDER_MEMBERS_CSS).click();
        ChannelMembersPage channelMembersPage = new ChannelMembersPage();
        channelMembersPage.isPageOpened();
        return channelMembersPage;
    }

    @Step("Clicking on [Create] button to create a new folder")
    public MenuStructurePage submitFolderCreation() {
        log.debug("Submit folder creation");
        MenuStructurePage menuStructure = new MenuStructurePage();
        $(CREATE_BUTTON_CSS).click();
        try {
            $(SEARCH_POSITION_FIELD_CSS).shouldNotBe(Condition.visible);
        } catch (Exception e) {
            log.error("Folder is not created. Cannot close the pop-up");
            screenshot("submit_create_folder_not_closed");
            Assert.fail("Impossible to proceed with folder creation: pop-up is not closed.");
        }
        return menuStructure;
    }

    @Step("Clicking on [Cancel] button to cancel creation of a new folder")
    public void cancelFolderCreation() {
        log.debug("Cancel folder creation");
        $(CANCEL_BUTTON_CSS).click();
        try {
            $(SEARCH_POSITION_FIELD_CSS).shouldNotBe(Condition.visible);
        } catch (Exception e) {
            log.error("Canceling folder creation failed. Cannot close the pop-up");
            screenshot("cancel_create_folder_not_closed");
            Assert.fail("Impossible to cancel folder creation: pop-up is not closed.");
        }
    }

    @Step("Clicking on [Cancel] button to cancel creation of a new folder")
    public void closeFolderCreationPopUp() {
        log.debug("Close 'Creating Folder' pop-up'");
        $(CLOSE_BUTTON_CSS).click();
        try {
            $(SEARCH_POSITION_FIELD_CSS).shouldNotBe(Condition.visible);
        } catch (Exception e) {
            log.error("Closing folder creation pop-up failed. Cannot close the pop-up");
            screenshot("create_folder_not_closed");
            Assert.fail("Impossible to cancel folder creation: pop-up is not closed.");
        }
    }

    public String getPositionErrorMessageText() {
        return $(By.xpath(INVALID_PATH_MESSAGE_XPATH)).getText();
    }

    public String getFolderNameErrorMessageText() {
        return $(By.xpath(NAME_ERROR_MESSAGE_CSS)).getText();
    }

    @Step("Check error '{text}' is displayed")
    public boolean validateErrorMessage(String text) {
        switch (text) {
            case "length":
                return getFolderNameErrorMessageText().equals(errorLength);
            case "unique":
                return getFolderNameErrorMessageText().equals(errorUnique);
            case "path":
                return getPositionErrorMessageText().equals(errorPath);
            default:
                return false;
        }
    }

    @Step("Fill in the 'Creating Folder' form with data")
    public CreateFolderPage fillInTheForm(Folder folderModel) {
        fillInPosition(folderModel.getPosition());
        fillInDescription(folderModel.getDescription());
        fillInFolderName(folderModel.getName());
        switchPrivacy(Boolean.parseBoolean(folderModel.getPrivacy()));
        selectSubchannelsSorting(folderModel.getSortingSubchannels());
        return this;
    }
}
