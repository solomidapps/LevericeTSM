package pages.addFolderPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static components.elements.Input.writeTextCss;

@Log4j2
public class SelectChannelLocationPage extends BasePage {
    private static final String SEARCH_LOCATION_FIELD_CSS = ".smart-bar-text.last-part-input";
    private static final String CLEAR_LOCATION_BUTTON_CSS = ".smartbar-clear-input";
    private static final String SHOW_FILTERS_BUTTON_CSS = ".bar-button.clickable show-filters";
    private static final String HIDE_NOT_SUBSCRIBED_CHANNELS_CSS = ".bar-button.clickable.show-not-subscribed-channels.bar-btn-active.has-tooltip";
    private static final String SHOW_NOT_SUBSCRIBED_CHANNELS_CSS = ".bar-button.clickable.show-not-subscribed-channels.has-tooltip";
    private static final String HIDE_ARCHIVED_CHANNELS_CSS = ".bar-button.clickable.show-archived-channels.bar-btn-active.has-tooltip";
    private static final String SHOW_ARCHIVED_CHANNELS_CSS = ".bar-button.clickable.show-archived-channels.has-tooltip";
    private static final String HIDE_POSSIBLE_TARGET_CHANNELS_CSS = ".bar-button.clickable.show-only-valid-targets.bar-btn-active.has-tooltip";
    private static final String SHOW_POSSIBLE_TARGET_CHANNELS_CSS = ".bar-button.clickable.show-only-valid-targets.has-tooltip";
    private static final String LIST_ITEM_XPATH = "//div[@class = 'flat-list-item']//span[contains(text(), '%s')]";
    private static final String LIST_ITEMS_CSS = ".flat-list-item";
    private static final String OK_BUTTON_CSS = ".button-accept.button-accept";
    private static final String CANCEL_BUTTON_CSS = ".button-cancel.flex middle center";
    private List<SelenideElement> channelList;


    @Step("Verifying Select Channel Location pop-up is opened ")
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

    @Step("Filling in 'Search Channel Location' field with '{text}'")
    public SelectChannelLocationPage fillInPosition(String text) {
        writeTextCss(SEARCH_LOCATION_FIELD_CSS, text);
        return this;
    }

    @Step("Clearing 'Search Channel Location' field")
    public SelectChannelLocationPage clearPosition() {
        log.debug("Clear 'Search Channel Location' field via [x] button");
        $(CLEAR_LOCATION_BUTTON_CSS).click();
        return this;
    }

    @Step("Click 'Show not subscribed channels' button in filters")
    public SelectChannelLocationPage filterShowNotSubscribed() {
        log.debug("Filter the displayed channels: show not subscribed channels");
        $(SHOW_FILTERS_BUTTON_CSS).click();
        $(HIDE_NOT_SUBSCRIBED_CHANNELS_CSS).click();
        return this;
    }

    @Step("Click 'Hide not subscribed channels' button in filters")
    public SelectChannelLocationPage filterHideNotSubscribed() {
        log.debug("Filter the displayed channels: hide not subscribed channels");
        $(SHOW_FILTERS_BUTTON_CSS).click();
        $(SHOW_NOT_SUBSCRIBED_CHANNELS_CSS).click();
        return this;
    }

    @Step("Click 'Show archived channels' button in filters")
    public SelectChannelLocationPage filterShowArchived() {
        log.debug("Filter the displayed channels: show archived channels");
        $(SHOW_FILTERS_BUTTON_CSS).click();
        $(HIDE_ARCHIVED_CHANNELS_CSS).click();
        return this;
    }

    @Step("Click 'Hide archived channels' button in filters")
    public SelectChannelLocationPage filterHideArchived() {
        log.debug("Filter the displayed channels: hide archived channels");
        $(SHOW_FILTERS_BUTTON_CSS).click();
        $(SHOW_ARCHIVED_CHANNELS_CSS).click();
        return this;
    }

    @Step("Click 'Show possible target channels' button in filters")
    public SelectChannelLocationPage filterShowPossibleTargetChannels() {
        log.debug("Filter the displayed channels: show only possible target channels");
        $(SHOW_FILTERS_BUTTON_CSS).click();
        $(HIDE_POSSIBLE_TARGET_CHANNELS_CSS).click();
        return this;
    }

    @Step("Click 'Hide possible target channels' button in filters")
    public SelectChannelLocationPage filterShowAllPossibleChannels() {
        log.debug("Filter the displayed channels: show all channels");
        $(SHOW_FILTERS_BUTTON_CSS).click();
        $(SHOW_POSSIBLE_TARGET_CHANNELS_CSS).click();
        return this;
    }

    @Step("Click '{channelName}' channel in the list")
    public SelectChannelLocationPage selectChannelByName(String channelName) {
        log.info("Selecting a channel to which the folder is going to be added");
        log.debug("Select " + channelName + " in channel structure");
        String THIS_CHANNEL_XPATH = String.format(LIST_ITEM_XPATH, channelName);
        try {
            $(By.xpath(THIS_CHANNEL_XPATH)).click();
        } catch (NoSuchElementException e) {
            log.error("There is no such channel in the list");
            Assert.fail("Impossible to proceed: required channel is not present in the list of channels");
        }
        return this;
    }

    @Step("Click '{channelNum}' channel in the list")
    public SelectChannelLocationPage selectChannelByNumberInList(int channelNum) {
        log.info("Selecting a channel to which the folder is going to be added");
        log.debug("Select " + channelNum + " channel in channel structure");
        channelList = $$(LIST_ITEMS_CSS);
        if (channelNum <= channelList.size()) {
            channelList.get(channelNum - 1).click();
        } else {
            log.error("Channel with required order number(" + channelNum + " ) is not found");
        }
        return this;
    }

    @Step("Submit selected channel location")
    public CreateFolderPage submitSelectedChannelPosition() {
        log.debug("Click on 'OK' to submit channel location");
        $(OK_BUTTON_CSS).click();
        CreateFolderPage createFolderPage = new CreateFolderPage();
        createFolderPage.isPageOpened();
        return createFolderPage;
    }

    @Step("Cancel channel location selecting")
    public CreateFolderPage cancelSelectedChannelPosition() {
        log.debug("Click on 'Cancel' to cancel channel location");
        $(CANCEL_BUTTON_CSS).click();
        CreateFolderPage createFolderPage = new CreateFolderPage();
        createFolderPage.isPageOpened();
        return createFolderPage;
    }

}
