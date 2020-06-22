package pages.inviteUsersPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import pages.addFolderPage.MenuStructurePage;
import pages.base.BasePage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static components.elements.Input.writeTextXpath;
import static org.testng.AssertJUnit.assertEquals;

@Log4j2
public class InviteUserModal extends BasePage {

    private static final String LOGO_CSS = ".logo-full";
    private static final String LIST_OF_USERS_CSS = ".email-block__email";
    private static final String INPUT_EMAIL_FIELD = "//textarea[@placeholder='Enter email addresses (or paste multiple)']";
    private static final String CONTINUE_BUTTON_CSS = ".button-accept";
    List<SelenideElement> emailList;

    @Step("Verifying is invite modal opened ")
    @Override
    public InviteUserModal isPageOpened() {
        log.debug("Check the 'Invite user' modal is displayed.");
        try {
            $(LOGO_CSS).shouldBe(Condition.visible);
        } catch (NoSuchElementException e) {
            log.error("'Invite user' modal is not opened.");
            screenshot("invite_user_modal_not_opened");
            Assert.fail("Impossible to invite a user: modal is not opened");
        }
        return this;
    }

    public InviteUserModal inputUser(String emailInput) {
        writeTextXpath(INPUT_EMAIL_FIELD, emailInput);
        return this;
    }

    public InviteUserModal getListOfUsers() {
        emailList = $$(LIST_OF_USERS_CSS);
        return this;
    }

    public InviteUserModal verifyUserIsInTheList(String expectedEmail) {
        String actualEmail = emailList.get(0).getText();
        assertEquals("Email is not in the list", actualEmail, expectedEmail);
        return this;
    }

    @Step("Clicking on continue button")
    public MenuStructurePage clickOnInviteeButton() {
        log.debug("Clicking continue button and waiting modal to disappear");
        $(CONTINUE_BUTTON_CSS).click();
        MenuStructurePage menuStructurePage = new MenuStructurePage();
        menuStructurePage.isPageOpened();
        return menuStructurePage;
    }

    //TODO Сделать метод для Sign In так как после создания воркспейса тест должен иметь возможность начаться независимо
    // при закрытии браузера нужно перелогиниваться
}
