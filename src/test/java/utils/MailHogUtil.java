package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import components.EmailComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class MailHogUtil {

    private static final String MAILHOG_URL = "https://autotest.leverice.net/email/#";
    private static final String EMAIL_LIST_CSS = ".msglist-message";
    private static final String TOOLBAR_CSS = ".toolbar";
    private static final String VALIDATION_CODE_IFRAME_ID = "preview-html";
    private static final String VALIDATION_CODE_CSS = ".validation-code";
    private static Map<Integer, EmailComponent> emailComponentMap = new LinkedHashMap<>();

    public static void openTab() {
        Selenide.executeJavaScript("window.open();");
        switchTo().window(1);
        open(MAILHOG_URL);
        isTabOpened();
    }

    public static void isTabOpened() {
        $(TOOLBAR_CSS).waitUntil(Condition.visible, 3000);
        AllureUtils.takeScreenshot();
    }

    public static void closeTab() {
        Selenide.executeJavaScript("window.close");
        switchTo().window(0);
    }

    public static void getAllEmails() {
        List<SelenideElement> emailList = $$(EMAIL_LIST_CSS);
        List<SelenideElement> emailNamesList = $$(EmailComponent.getEmailToClassname());
        List<SelenideElement> subjectNameList = $$(EmailComponent.getSubjectClassname());
        if (emailList.isEmpty()) {
            Assert.fail("Email list is empty");
        } else {
            for (int i = 0; i < emailList.size(); i++) {
                emailComponentMap.put(i,
                        new EmailComponent(
                                emailNamesList.get(i),
                                subjectNameList.get(i)));
            }
        }

    }

    public static void openEmailByNameAndASubject(String emailName, String subject) {
        for (int i = 0; i < emailComponentMap.size(); i++) {
            if (emailComponentMap.get(i).getEmailToName().equals(emailName) && emailComponentMap.get(i).getSubjectName().equals(subject)) {
                emailComponentMap.get(i).clickOnEmail();
            } else if (i == emailComponentMap.size() - 1) {
                Assert.fail("No such email in the list: " + emailName);
            }
        }
    }

    public static String getValidationCode() {
        switchTo().frame($(By.id(VALIDATION_CODE_IFRAME_ID)));
        AllureUtils.takeScreenshot();
        return $(VALIDATION_CODE_CSS).getText().trim();
    }

    public static String getValidationCodeByEmail(String emailName) {
        openTab();
        getAllEmails();
        openEmailByNameAndASubject(emailName, "Leverice email validation");
        String validationCode = getValidationCode();
        closeTab();
        return validationCode;
    }
}
