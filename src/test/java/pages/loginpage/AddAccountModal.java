package pages.loginpage;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import pages.base.BaseMenuModal;
import utils.AllureUtils;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

@Log4j2
public class AddAccountModal extends BaseMenuModal {

    private static final String BACK_BUTTON_CSS = ".back-button";
    private static final String CONTINUE_BUTTON_CSS = ".button-accept";
    private static final String INPUT_AREA_CSS = ".textarea__inner";

    @Override
    public BaseMenuModal isModalOpened() {
        log.info("Checking is Add account modal opened");
        try {
            $(BACK_BUTTON_CSS).waitUntil(Condition.visible, 3000);

        } catch (NoSuchElementException e) {
            log.error("Add account modal is not opened");
            AllureUtils.takeScreenshot();
        }
        return this;
    }

    public MainMenuModal clickOnBackButton() {
        $(BACK_BUTTON_CSS).click();
        return new MainMenuModal();
    }

    public AddAccountModal enterEmail(String email) {
        log.info("Filling in field with text: " + email);
        assertTrue(isContinueButtonDisabled());
        $(INPUT_AREA_CSS).clear();
        $(INPUT_AREA_CSS).sendKeys(email);

        return this;
    }

    public boolean isContinueButtonDisabled() {
        return $(CONTINUE_BUTTON_CSS).getAttribute("class").contains("disabled");
    }

    public boolean isContinueButtonEnabled() {
        return $(CONTINUE_BUTTON_CSS).getAttribute("class").contains("active");
    }

    public VerifyAccountModal clickOnContinueButton() {
        log.info("Clicking on continue button");
        assertTrue(isContinueButtonEnabled());
        $(CONTINUE_BUTTON_CSS).click();
        VerifyAccountModal verifyAccountModal = new VerifyAccountModal();
        verifyAccountModal.isModalOpened();
        return verifyAccountModal;
    }
}


