package steps;

import io.qameta.allure.Step;
import pages.mainMenuPage.*;

public class MainMenuSteps {
    private MainMenuModal mainMenuModal;
    private AddAccountModal addAccountModal;
    private JoinByTokenModal joinByTokenModal;
    private VerifyAccountModal verifyAccountModal;
    private IntroduceModal introduceModal;
    private CreateWorkspaceModal createWorkspaceModal;

    public MainMenuSteps() {
        mainMenuModal = new MainMenuModal();
        addAccountModal = new AddAccountModal();
        joinByTokenModal = new JoinByTokenModal();
        verifyAccountModal = new VerifyAccountModal();
        introduceModal = new IntroduceModal();
        createWorkspaceModal = new CreateWorkspaceModal();
    }

    @Step("Creating new workspace for email '{emailName}' and name '{workspaceName}'")
    public MainMenuSteps createNewWorkspace(String emailName, String firstName, String lastName, String workspaceName) {
        mainMenuModal
                .openPage()
                .clickOnCreateNewWorkspace();
        addAccountModal
                .enterEmail(emailName)
                .proceedToToVerifyAccount();
        verifyAccountModal
                .setVerificationCodeFromEmail(emailName);
        introduceModal
                .inputFirstName(firstName)
                .inputLastName(lastName)
                .clickOnContinueButton();
        createWorkspaceModal
                .enterWorkspaceName(workspaceName)
                .clickOnContinueButton();
        return this;
    }
}
