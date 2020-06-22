package pages.loginpage;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.base.BaseMenuModal;
import pages.base.MenuStructurePage;
import utils.AllureUtils;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class CreateWorkspaceModal extends BaseMenuModal {

    private static final String INTRO_HEADING_CSS = ".heading";
    private static final String INPUT_WORKSPACE_NAME_CSS = ".textarea__inner ";
    private static final String CONTINUE_BUTTON_CSS = ".button-accept";

    @Step("Verifying is CreateWorkspaceModal open")
    @Override
    public BaseMenuModal isModalOpened() {
        log.info("Checking create workspace modal opened");
        try {
            $(INTRO_HEADING_CSS).waitUntil(Condition.visible, 3000);

        } catch (NoSuchElementException e) {
            log.error("Create workspace modal is not opened");
            AllureUtils.takeScreenshot();
        }
        return this;
    }

    @Step("Entering workspace name '{workspaceName}'")
    public CreateWorkspaceModal enterWorkspaceName(String workspaceName) {
        log.info("Enetring workspace name " + workspaceName);
        $(INPUT_WORKSPACE_NAME_CSS).sendKeys(workspaceName);
        return this;
    }

    @Step("Clicking on continue button")
    public MenuStructurePage clickOnContinueButton(){
        log.info("Clicking on continue button");
        $(CONTINUE_BUTTON_CSS).click();
        return new MenuStructurePage();
    }
}
