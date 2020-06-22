package pages.loginpage;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import pages.base.BaseMenuModal;
import utils.AllureUtils;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class CreateWorkspaceModal extends BaseMenuModal {

    private static final String INTRO_HEADING_CSS = ".heading";
    private static final String INPUT_WORKSPACE_NAME_CSS = ".textarea__inner ";
    private static final String CONTINUE_BUTTON_CSS = ".button-accept";

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

    public void enterWorkspaceName(String workspaceName) {
        $(INPUT_WORKSPACE_NAME_CSS).sendKeys(workspaceName);
    }

    public void clickOnContinueButton(){
        $(CONTINUE_BUTTON_CSS).click();
    }
}
