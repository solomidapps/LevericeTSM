package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.Random;

public class MainMenuTest extends BaseTest {

    private static final String randomNumber = String.valueOf(Math.random() * ((999 - 1) + 1)) + 1;

    @Test(description = "Verifying user can create a new workspace")
    public void verifyUserCanCreateWorkspace() {
        mainMenuSteps.createNewWorkspace(
                "wonder" + randomNumber + "@day.com",
                "TMS-Name",
                "TMS-Surname",
                "TMS-Workspace");
    }
}
