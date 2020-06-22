package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class InviteUserTest extends BaseTest {

    @Test(description = "Verifying user can invite another user")
    public void verifyUserCanBeInvited() {
        inviteUserSteps.verifyNewUserCanBeInvited("tms-test@mail.com");
    }
}
