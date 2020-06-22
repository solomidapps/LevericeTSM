package pages.addFolderPage;

import com.codeborne.selenide.Condition;
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
    private static final String SEARCH_POSITION_FIELD_CSS = ".searchbar";
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

    public CreateFolderPage fillInPosition(String text) {
        writeTextCss(SEARCH_POSITION_FIELD_CSS, text);
        return this;
    }

    public CreateFolderPage clearPosition() {
        log.debug("Clear 'Position' field via [x] button");
        $(CLEAR_BUTTON_CSS).click();
        return this;
    }


    public CreateFolderPage fillInFolderName(String text) {
        writeTextXpath(FOLDER_NAME_FIELD_XPATH, text);
        return this;
    }

    public CreateFolderPage switchPrivacy(boolean isPrivate) {
        log.debug("Set 'Make Folder Private' to " + isPrivate);
        boolean currentState = $(PRIVATE_TOGGLE_ON_CSS).isDisplayed();
        if (currentState && !isPrivate) {
            $(By.xpath(MAKE_PRIVATE_TOGGLE_XPATH)).click();
        }
        return this;
    }

    public CreateFolderPage openOptionsSection() {
        $(OPTIONS_BUTTON_CSS).click();
        isOptionSectionOpened();
        return this;
    }

    public CreateFolderPage fillInDescription(String text) {
        writeTextXpath(FOLDER_DESCRIPTION_FIELD_XPATH, text);
        return this;
    }

    public CreateFolderPage selectSubchannelsSorting(String option) {
        log.info("Open 'Sort Subchannels' dropdown");
        $(SORT_SUBCHANNELS_DROPDOWN_CSS).click();
        log.debug("Select " + option + " in 'Sort Subchannels' dropdown");
        String THIS_OPTION_XPATH = String.format(SORT_SUBCHANNELS_DROPDOWN_ITEM_XPATH, option);
        $(By.xpath(THIS_OPTION_XPATH)).click();
        return this;
    }

    public SelectChannelLocationPage goToSelectPositionMenu() {
        log.debug("Open Select Channel Location Page from Creating Folder pop-up");
        $(GO_TO_SELECT_CHANNEL_BUTTON_CSS).click();
        SelectChannelLocationPage selectChannelLocationPage = new SelectChannelLocationPage();
        selectChannelLocationPage.isPageOpened();
        return selectChannelLocationPage;
    }

    public ChannelMembersPage goToSelectChannelMembersMenu() {
        log.debug("Open Select Channel Members Page from Creating Folder pop-up");
        $(FOLDER_MEMBERS_CSS).click();
        ChannelMembersPage channelMembersPage = new ChannelMembersPage();
        channelMembersPage.isPageOpened();
        return channelMembersPage;
    }

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

    public CreateFolderPage fillInTheForm(Folder folderModel){
        fillInPosition(folderModel.getPosition());
        fillInDescription(folderModel.getDescription());
        fillInFolderName(folderModel.getName());
        switchPrivacy(Boolean.parseBoolean(folderModel.getPrivacy()));
        selectSubchannelsSorting(folderModel.getSortingSubchannels());
        return this;
    }
}
