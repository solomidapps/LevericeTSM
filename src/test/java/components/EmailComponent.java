package components;

import com.codeborne.selenide.SelenideElement;

public class EmailComponent {
    private SelenideElement emailTo;
    private static final String EMAIL_TO_CLASSNAME = "[class='ng-binding ng-scope']";

    public EmailComponent(SelenideElement emailTo) {
        this.emailTo = emailTo;
    }

    public static String getEmailToClassname() {
        return EMAIL_TO_CLASSNAME;
    }

    public String getEmailToName() {
        return emailTo.getText();
    }

    public void clickOnEmail() {
        emailTo.click();
    }
}
