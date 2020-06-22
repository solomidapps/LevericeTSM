package pages.loginpage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.testng.Assert;
import pages.base.BaseMenuModal;
import utils.AllureUtils;
import utils.MailHogUtil;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class VerifyAccountModal extends BaseMenuModal {

    private static final String BACK_BUTTON_CSS = ".back-button";
    private static final String INPUT_ELEMENTS_XPATH = "//input[@inputmode='numeric']";
    private static final String CONTINUE_BUTTON_CSS = ".button-accept";
    private ElementsCollection element;

    @Step("Opening VerifyAccountModal open")
    @Override
    public BaseMenuModal isModalOpened() {
        log.info("Checking is Verification modal opened");
        try {
            $(BACK_BUTTON_CSS).waitUntil(Condition.visible, 3000);
        } catch (NoSuchElementException e) {
            log.error("Verification modal is not opened");
            AllureUtils.takeScreenshot();
        }
        return this;
    }

    @Step("Clicking on back button")
    public MainMenuModal clickOnBackButton() {
        $(BACK_BUTTON_CSS).click();
        return new MainMenuModal();
    }

    @Step("Getting verification code")
    public VerifyAccountModal getVerificationFields() {
        log.info("Getting verification fields");
        try {
            screenshot("test.png");
            element = $$(By.xpath(INPUT_ELEMENTS_XPATH));
        } catch (Exception e) {
            log.error("Couldn't find elements");
            Assert.fail("No such elements");
            AllureUtils.takeScreenshot();
        }

        return this;
    }

    @Step("Inputting verification code")
    public VerifyAccountModal enterCodeInFirstField(String verificationCode) {
        log.info("Inputting verification code");
        try {
            element.get(0).sendKeys(verificationCode);
        } catch (ElementNotInteractableException e) {
            log.error("Could not enter verification code");
            Assert.fail("Could not enter verification code");
            AllureUtils.takeScreenshot();
        }
        return this;
    }

    @Step("Setting verification code from email '{emailName}'")
    public IntroduceModal setVerificationCodeFromEmail(String emailName) {
        getVerificationFields().enterCodeInFirstField(MailHogUtil.getValidationCodeByEmail(emailName));
        IntroduceModal introduceModal = new IntroduceModal();
        introduceModal.isModalOpened();
        return introduceModal;
    }
}