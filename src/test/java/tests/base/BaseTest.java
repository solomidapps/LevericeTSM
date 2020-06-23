package tests.base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;
import pages.addFolderPage.MenuStructurePage;
import pages.inviteUsersPage.InviteUserModal;
import pages.mainMenuPage.*;
import steps.InviteUserSteps;
import steps.MainMenuSteps;
import utils.TestListener;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {

    protected MainMenuModal mainMenuModal;
    protected AddAccountModal addAccountModal;
    protected JoinByTokenModal joinByTokenModal;
    protected VerifyAccountModal verifyAccountModal;
    protected IntroduceModal introduceModal;
    protected CreateWorkspaceModal createWorkspaceModal;
    protected MainMenuSteps mainMenuSteps;
    protected InviteUserModal inviteUserModal;
    protected MenuStructurePage menuStructurePage;
    protected InviteUserSteps inviteUserSteps;

    private static final String randomNumber = String.valueOf(Math.random() * ((999 - 1) + 1)) + 1;
    private static final String preCreatedEmail = "wonder-pre" + randomNumber + "@day.com";
    private static final String preCreatedWorkspaceName = "TMS-Pre-Workspace2";

    public static String getPreCreatedEmail() {
        return preCreatedEmail;
    }

    public static String getPreCreatedWorkspaceName() {
        return preCreatedWorkspaceName;
    }

    @BeforeSuite
    public void setupEnvironment() {
        MainMenuSteps mainMenuSteps = new MainMenuSteps();
        mainMenuSteps.createNewWorkspace(
                preCreatedEmail,
                "TMS-Pre-Name",
                "TMS-Pre-Surname",
                preCreatedWorkspaceName);
        getWebDriver().quit();
    }

    @BeforeMethod
    public void setupBrowser() {
        Configuration.headless = false;
        Configuration.startMaximized = true;
        Configuration.timeout = 20000;
        Configuration.browser = "chrome";
        Configuration.clickViaJs = true;
        mainMenuModal = new MainMenuModal();
        addAccountModal = new AddAccountModal();
        joinByTokenModal = new JoinByTokenModal();
        verifyAccountModal = new VerifyAccountModal();
        introduceModal = new IntroduceModal();
        createWorkspaceModal = new CreateWorkspaceModal();
        mainMenuSteps = new MainMenuSteps();
        introduceModal = new IntroduceModal();
        menuStructurePage = new MenuStructurePage();
        inviteUserModal = new InviteUserModal();
        inviteUserSteps = new InviteUserSteps();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        getWebDriver().quit();
    }


}

