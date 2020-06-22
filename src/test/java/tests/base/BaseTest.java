package tests.base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.loginpage.*;
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
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        getWebDriver().quit();
    }
}

