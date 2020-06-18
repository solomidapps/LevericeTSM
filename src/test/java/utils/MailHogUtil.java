package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import components.EmailComponent;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class MailHogUtil {

    private static final String MAILHOG_URL = "https://autotest.leverice.net/email/#";
    private static final String EMAIL_LIST_CSS = ".msglist-message";
    private static final String TOOLBAR_CSS = ".toolbar";
    private static final String VALIDATION_CODE_IFRAME_ID = ".preview-html";
    private static final String VALIDATION_CODE_CSS = ".validation-code";
    private static Map<Integer, EmailComponent> emailComponentMap = new LinkedHashMap<>();

    @Step("Opening MailHog in a new tab")
    public static void openTab() {
        Selenide.executeJavaScript("window.open();");
        switchTo().window(1);
        open(MAILHOG_URL);
        isTabOpened();
    }

    public static void isTabOpened() {
        try {
            $(TOOLBAR_CSS).waitUntil(Condition.visible, 3000);
            AllureUtils.takeScreenshot();
        } catch (ElementShould ex) {
            Assert.fail("Page has not been load");
            AllureUtils.takeScreenshot();
        }
    }

    @Step("Getting all emails in a list")
    public static void getAllEmails() {
        List<SelenideElement> emailList = $$(EMAIL_LIST_CSS);
        List<SelenideElement> emailNamesList = $$(EmailComponent.getEmailToClassname());
        for (int i = 0; i < emailList.size(); i++) {
            emailComponentMap.put(i, new EmailComponent(emailNamesList.get(i)));
        }
    }

    @Step("Opening first matching to '{emailName}' email in the list")
    public static void openEmailByName(String emailName) {
        try {
            for (int i = 0; i < emailComponentMap.size(); i++) {
                if (emailComponentMap.get(i).getEmailToName().equals(emailName)) {
                    emailComponentMap.get(i).clickOnEmail();
                }
            }
        } catch (ElementShould elementShould) {
            Assert.fail("No such email on the page");
            AllureUtils.takeScreenshot();
        }
    }

    @Step("Getting verification code from '{emailName}'")
    public String getValidationCode(String emailName) {
        openTab();
        getAllEmails();
        openEmailByName(emailName);
        switchTo().frame($(VALIDATION_CODE_IFRAME_ID));
        AllureUtils.takeScreenshot();
        closeTab();
        return $(VALIDATION_CODE_CSS).getText().trim();
    }

    @Step("Closing MailHog tab")
    public static void closeTab() {
        Selenide.executeJavaScript("window.close");
        switchTo().window(0);
    }
}
