package steps;

import io.qameta.allure.Step;
import pages.addFolderPage.MenuStructurePage;
import pages.inviteUsersPage.InviteUserModal;
import pages.mainMenuPage.*;
import tests.base.BaseTest;
import utils.MailHogUtil;

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
        /*introduceModal
                .inputFirstName("tsm-test-f")
                .inputLastName("tsm-test-l")
                .proceedToSelectWorkspace();*/
        openWorkspaceModal
                .getWorkspaces().clickOnFirstWorkspace();
        menuStructurePage.clickInviteUserButton();
        inviteUserModal
                .inputUser(emailToInvite)
                .getListOfUsers().verifyUserIsInTheList(emailToInvite)
                .clickOnInviteeButton();
        MailHogUtil.clickOnJoinWorkspaceInEmail(emailToInvite);
        menuStructurePage.isPageOpened();
        return this;
    }
}
