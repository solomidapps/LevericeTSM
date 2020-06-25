package pages.addFolderPage;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pages.base.BasePage;
import pages.inviteUsersPage.InviteUserModal;
import utils.AllureUtils;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.screenshot;

@Log4j2
public class MenuStructurePage extends BasePage {

    private static final String OPEN_MODAL_XPATH = "//div[@class='command-link' and div[contains(text(),'%s')]]";

    @Step("Verifying is MenuStructurePage is opened")
    @Override
    public BasePage isPageOpened() {
        log.debug("Check the 'MenuStructurePage' is displayed.");
        try {
            $(By.xpath(String.format(OPEN_MODAL_XPATH, "Create new Folder"))).waitUntil(Condition.visible, 5000);
            AllureUtils.takeScreenshot();
        } catch (NoSuchElementException e) {
            log.error("'MenuStructurePage' is not opened.");
            screenshot("main_menu_structure_is_not_opened");
            Assert.fail("'MenuStructurePage' cannot be opened");
        }
        return this;
    }

    @Step("Clicking on invite user button")
    public InviteUserModal clickInviteUserButton() {
        $(By.xpath(String.format(OPEN_MODAL_XPATH, "Invite User"))).click();
        AllureUtils.takeScreenshot();
        InviteUserModal inviteUserModal = new InviteUserModal();
        inviteUserModal.isPageOpened();
        return inviteUserModal;
    }
}
