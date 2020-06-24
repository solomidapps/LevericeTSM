package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.Random;

public class MainMenuTest extends BaseTest {

    private static final String randomNumber = String.valueOf(Math.random() * ((999 - 1) + 1)) + 1;
    private static final String defaultUserEmail = "tms-test@mail.com";

    @Test(description = "Verifying user can create a new workspace")
    public void verifyUserCanCreateWorkspace() {
        mainMenuSteps.createNewWorkspace(
                "wonder" + randomNumber + "@day.com",
                "TMS-Name",
                "TMS-Surname",
                "TMS-Workspace");
    }

    @Test(description = "Verifying user can sign in")
    public void verifyUserCanSignIn() {
        mainMenuSteps.verifyUserCanSignIn(defaultUserEmail);
    }

    @Test(description = "Verifyin user can sign in with token")
    public void verifyUserCanSogInWithToken() {
        mainMenuSteps.verifyUserCanSignInWithToken(defaultUserEmail);
    }
}
