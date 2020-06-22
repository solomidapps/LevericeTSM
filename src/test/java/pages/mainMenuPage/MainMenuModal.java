package pages.mainMenuPage;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import pages.base.BaseMenuModal;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static components.elements.MainMenuButton.clickMenuButtonByText;
import static components.elements.MainMenuButton.validateButtonDescription;


import lombok.extern.log4j.Log4j2;
import utils.AllureUtils;

import java.util.NoSuchElementException;

@Log4j2
public class MainMenuModal extends BaseMenuModal {

    private static final String TUTORIAL_IMG_CSS = ".tutorial-img";

    //Expected buttons descriptions;
    private static final String creatWorkspaceDesc = "Create a new Workspace";
    private static final String signInDesc = "Open a Workspace you already work in.";
    private static final String singInWithTokenDesc = "Join your teammates using the invite token you received.";

    @Step("Opening MainMenuModal open")
    public MainMenuModal openPage() {
        log.info("Open Main Menu modal");
        open("https://autotest.leverice.net/public/client/");
        return this;
    }

    @Step("Verifying is MainMenuModal open")
    @Override
    public BaseMenuModal isModalOpened() {
        log.info("Checking is Main Menu modal opened");
        try {
            $(TUTORIAL_IMG_CSS).waitUntil(Condition.visible, 3000);

        } catch (NoSuchElementException e) {
            log.error("Main menu modal is not opened");
            AllureUtils.takeScreenshot();
        }
        return this;
    }

    @Step("Click on create workspace button")
    public AddAccountModal clickOnCreateNewWorkspace() {
        log.info("Opening create new workspace modal");
        validateButtonDescription("Try Leverice, for free", creatWorkspaceDesc);
        clickMenuButtonByText("Try Leverice, for free");
        AddAccountModal addAccountModal = new AddAccountModal();
        addAccountModal.isModalOpened();
        return addAccountModal;
    }

    @Step("Click on sign in button")
    public AddAccountModal signIn() {
        log.info("Opening sign in modal");
        validateButtonDescription("Sign in", signInDesc);
        clickMenuButtonByText("Sign in");
        AddAccountModal addAccountModal = new AddAccountModal();
        addAccountModal.isModalOpened();
        return addAccountModal;
    }

    @Step("Click on sign in with token button")
    public JoinByTokenModal signInWithToken() {
        log.info("Opening sign in with token modal");
        validateButtonDescription("Sign up with token", singInWithTokenDesc);
        clickMenuButtonByText("Sign up with token");
        JoinByTokenModal joinByTokenModal = new JoinByTokenModal();
        joinByTokenModal.isModalOpened();
        return joinByTokenModal;
    }
}
