package steps;

import io.qameta.allure.Step;
import pages.addFolderPage.MenuStructurePage;
import pages.inviteUsersPage.InviteUserModal;

public class InviteUserSteps {
    private MenuStructurePage menuStructurePage;
    private InviteUserModal inviteUserModal;

    public InviteUserSteps() {
        menuStructurePage = new MenuStructurePage();
        inviteUserModal = new InviteUserModal();
    }

    @Step("Inviting a new user")
    public InviteUserSteps verifyNewUserCanBeInvited(String emailToInvite) {
        menuStructurePage.clickInviteUserButton();
        inviteUserModal
                .inputUser(emailToInvite)
                .getListOfUsers().verifyUserIsInTheList(emailToInvite)
                .clickOnInviteeButton();
        return this;
    }
}
