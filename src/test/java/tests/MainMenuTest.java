package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class MainMenuTest extends BaseTest {

    @Test
    public void verifyUserCanCreateWorkspace(){
        mainMenuModal
                .openPage()
                .createNewWorkspace();
        addAccountModal
                .enterEmail("fromAuto@test.com")
                .clickOnContinueButton();
        verifyAccountModal
                .setVerificationCodeFromEmail("fromauto@test.com")
                .clickOnContinueButton();
        introduceModal
                .inputFirstName("tttt")
                .inputLastName("ttttt")
                .clickOnContinueButton();
        createWorkspaceModal
                .enterWorkspaceName("tereee");
        createWorkspaceModal.clickOnContinueButton();

    }
}
