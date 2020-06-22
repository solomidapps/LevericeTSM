package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class MainMenuTest extends BaseTest {

    @Test(description = "Verifying user can create a new workspace")
    public void verifyUserCanCreateWorkspace() {
        mainMenuSteps.createNewWorkspace(
                "wonder@day.com",
                "TMS-Name",
                "TMS-Surname",
                "TMS-Workspace");
    }
}
