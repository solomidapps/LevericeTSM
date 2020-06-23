package pages.mainMenuPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import pages.addFolderPage.MenuStructurePage;
import pages.base.BaseMenuModal;
import pages.inviteUsersPage.InviteUserModal;
import utils.AllureUtils;

import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.AssertJUnit.assertEquals;

@Log4j2
public class OpenWorkspaceModal extends BaseMenuModal {

    private static final String WORKSPACES_HEADING_XPATH = "//div[@class='heading block' and text()='Please select your workspace name']";
    private static final String WORKSPACES_LIST_CSS = ".workspace-block";
    private List<SelenideElement> workspacesList;

    @Override
    public OpenWorkspaceModal isModalOpened() {
        log.debug("Checking is OpenWorkspaceModal modal opened");
        try {
            $(By.xpath(WORKSPACES_HEADING_XPATH)).waitUntil(Condition.visible, 3000);
        } catch (NoSuchElementException e) {
            log.error("OpenWorkspaceModal modal is not opened");
            AllureUtils.takeScreenshot();
        }
        return this;
    }

    public OpenWorkspaceModal getWorkspaces() {
        workspacesList = $$(WORKSPACES_LIST_CSS);
        return this;
    }

    public MenuStructurePage clickOnFirstWorkspace() {
        workspacesList.get(0).click();
        MenuStructurePage menuStructurePage = new MenuStructurePage();
        menuStructurePage.isPageOpened();
        return menuStructurePage;
    }

}
