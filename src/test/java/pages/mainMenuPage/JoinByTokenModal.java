package pages.mainMenuPage;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.addFolderPage.MenuStructurePage;
import pages.base.BaseMenuModal;
import utils.AllureUtils;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static components.elements.Input.writeTextXpath;
import static utils.MailHogUtil.getInvitationTokenByEmail;

@Log4j2
public class JoinByTokenModal extends BaseMenuModal {

    private static final String BACK_BUTTON_CSS = ".wizard-nav-header";
    private static final String TEXT_AREA_XPATH = "//textarea[@placeholder= 'Authentication token...']";
    private static final String CONTINUE_BUTTON_CSS = ".button-accept";

    @Step("Verifying is JoinByTokenModal open")
    @Override
    public JoinByTokenModal isModalOpened() {
        log.info("Checking JoinByTokenModal opened");
        try {
            $(BACK_BUTTON_CSS).waitUntil(Condition.visible, 3000);

        } catch (NoSuchElementException e) {
            log.error("Main menu modal is not opened");
            AllureUtils.takeScreenshot();
        }
        return this;
    }

    @Step("Entering token")
    public JoinByTokenModal enterToken(String token) {
        writeTextXpath(TEXT_AREA_XPATH, token);
        return this;
    }
    
    @Step("Inputting token from email")
    public JoinByTokenModal inputTokenFromEmail(String email){
        String token = getInvitationTokenByEmail(email);
        enterToken(token);
        return this;
    }
    @Step("Clicking on Join button")
    public MenuStructurePage clickOnJoinButton(){
        log.debug("Clicking on Join button");
        $(CONTINUE_BUTTON_CSS).click();
        MenuStructurePage menuStructurePage = new MenuStructurePage();
        menuStructurePage.isPageOpened();
        return menuStructurePage;
    }
}
