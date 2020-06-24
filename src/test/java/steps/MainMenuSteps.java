package steps;

import io.qameta.allure.Step;
import pages.addFolderPage.MenuStructurePage;
import pages.mainMenuPage.*;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MainMenuSteps {
    private MainMenuModal mainMenuModal;
    private AddAccountModal addAccountModal;
    private JoinByTokenModal joinByTokenModal;
    private VerifyAccountModal verifyAccountModal;
    private IntroduceModal introduceModal;
    private CreateWorkspaceModal createWorkspaceModal;
    private OpenWorkspaceModal openWorkspaceModal;
    private MenuStructurePage menuStructurePage;
    private InviteUserSteps inviteUserSteps;

    public MainMenuSteps() {
        mainMenuModal = new MainMenuModal();
        addAccountModal = new AddAccountModal();
        joinByTokenModal = new JoinByTokenModal();
        verifyAccountModal = new VerifyAccountModal();
        introduceModal = new IntroduceModal();
        createWorkspaceModal = new CreateWorkspaceModal();
        openWorkspaceModal = new OpenWorkspaceModal();
        menuStructurePage = new MenuStructurePage();
        inviteUserSteps = new InviteUserSteps();
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

    @Step("Verifying user can Sign in")
    public MainMenuSteps verifyUserCanSignIn(String email) {
        mainMenuModal
                .openPage()
                .clickOnSignIn();
        addAccountModal
                .enterEmail(email)
                .proceedToToVerifyAccount();
        verifyAccountModal
                .setCodeFromEmailAndProceedToOpenWorkspace(email);
        openWorkspaceModal
                .getWorkspaces().clickOnFirstWorkspace();
        sleep(2000);
        menuStructurePage.isPageOpened();
        return this;
    }

    @Step("Verifying user can sign in with token")
    public void verifyUserCanSignInWithToken(String email) {
        inviteUserSteps.verifyNewUserCanBeInvited(email);
        getWebDriver().quit();
        mainMenuModal
                .openPage()
                .clickSignInWithToken();
        joinByTokenModal
                .inputTokenFromEmail(email)
                .clickOnJoinButton();

    }
}
