package steps;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import pages.addFolderPage.MenuStructurePage;
import pages.inviteUsersPage.InviteUserModal;
import pages.mainMenuPage.*;
import tests.base.BaseTest;
import utils.MailHogUtil;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class InviteUserSteps {
    private MenuStructurePage menuStructurePage;
    private InviteUserModal inviteUserModal;
    private MainMenuModal mainMenuModal;
    private AddAccountModal addAccountModal;
    private IntroduceModal introduceModal;
    private VerifyAccountModal verifyAccountModal;
    private OpenWorkspaceModal openWorkspaceModal;

    public InviteUserSteps() {
        menuStructurePage = new MenuStructurePage();
        inviteUserModal = new InviteUserModal();
        mainMenuModal = new MainMenuModal();
        addAccountModal = new AddAccountModal();
        introduceModal = new IntroduceModal();
        verifyAccountModal = new VerifyAccountModal();
        openWorkspaceModal = new OpenWorkspaceModal();
    }

    @Step("Inviting a new user")
    public InviteUserSteps verifyNewUserCanBeInvited(String emailToInvite) {
        mainMenuModal
                .openPage()
                .clickOnSignIn();
        addAccountModal
                .enterEmail(BaseTest.getPreCreatedEmail())
                .proceedToToVerifyAccount();
        verifyAccountModal
                .setCodeFromEmailAndProceedToOpenWorkspace(BaseTest.getPreCreatedEmail());
        openWorkspaceModal
                .getWorkspaces().clickOnFirstWorkspace();
        menuStructurePage.clickInviteUserButton();
        inviteUserModal
                .inputUser(emailToInvite)
                .getListOfUsers().verifyUserIsInTheList(emailToInvite)
                .clickOnInviteeButton();
        sleep(4000);
        menuStructurePage.isPageOpened();
        MailHogUtil.clickOnJoinWorkspaceInEmail(emailToInvite);
        Selenide.executeJavaScript("window.resizeTo(1024, 768);");
      //  menuStructurePage.isPageOpened();
      /*  introduceModal
                .inputFirstName("tsm-test-f")
                .inputLastName("tsm-test-l")
                .proceedToMenuStructurePage();*/
        return this;
    }
}
