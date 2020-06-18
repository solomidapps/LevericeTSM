package components;

import com.codeborne.selenide.SelenideElement;

public class EmailComponent {
    private SelenideElement emailTo;
    private SelenideElement subject;
    private static final String EMAIL_TO_CLASSNAME = "[class='ng-binding ng-scope']";
    private static final String SUBJECT_CLASSNAME = "[class='subject unread ng-binding']";

    public EmailComponent(SelenideElement emailTo, SelenideElement subject) {
        this.emailTo = emailTo;
        this.subject = subject;
    }

    public static String getEmailToClassname() {
        return EMAIL_TO_CLASSNAME;
    }

    public static String getSubjectClassname() {
        return SUBJECT_CLASSNAME;
    }

    public String getEmailToName() {
        return emailTo.getText();
    }

    public String getSubjectName() {
        return subject.getText();
    }

    public void clickOnEmail() {
        emailTo.click();
    }
}
