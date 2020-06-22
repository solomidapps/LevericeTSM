package pages.loginpage;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import pages.base.BaseMenuModal;
import utils.AllureUtils;

import java.util.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class IntroduceModal extends BaseMenuModal {

    private static final String INTRO_HEADING_CSS = ".heading";
    private static final String FIRST_NAME_INPUT__CSS = "[name='fname']";
    private static final String SECOND_NAME_INPUT__CSS = "[name='lname']";
    private static final String CONTINUE_BUTTON_CSS = ".button-accept";

    @Override
    public BaseMenuModal isModalOpened() {
        log.info("Checking Intro modal opened");
        try {
            $(INTRO_HEADING_CSS).waitUntil(Condition.visible, 3000);

        } catch (NoSuchElementException e) {
            log.error("Intro modal is not opened");
            AllureUtils.takeScreenshot();
        }
        return this;
    }

    public IntroduceModal inputFirstName(String name) {
        $(FIRST_NAME_INPUT__CSS).sendKeys(name);
        return this;
    }

    public IntroduceModal inputLastName(String name) {
        $(SECOND_NAME_INPUT__CSS).sendKeys(name);
        return this;
    }

    public void clickOnContinueButton(){
        $(CONTINUE_BUTTON_CSS).click();
    }

}
