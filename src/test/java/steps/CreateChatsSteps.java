package steps;

import io.qameta.allure.Step;
import pages.addNewTopic.CreateTopicChannelPage;
import pages.createDirectChannelPage.CreateDirectChannelPage;
import pages.mainMenuPage.*;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CreateChatsSteps {
    private MainMenuModal mainMenuModal;
    private AddAccountModal addAccountModal;
    private JoinByTokenModal joinByTokenModal;
    private VerifyAccountModal verifyAccountModal;
    private IntroduceModal introduceModal;
    private CreateWorkspaceModal createWorkspaceModal;
    private CreateTopicChannelPage topicChannel;
    private CreateDirectChannelPage createDirectChannelPage;
    private MainMenuSteps mainMenuSteps;

    public CreateChatsSteps() {
        mainMenuModal = new MainMenuModal();
        addAccountModal = new AddAccountModal();
        joinByTokenModal = new JoinByTokenModal();
        verifyAccountModal = new VerifyAccountModal();
        introduceModal = new IntroduceModal();
        createWorkspaceModal = new CreateWorkspaceModal();
        topicChannel = new CreateTopicChannelPage();
        createDirectChannelPage = new CreateDirectChannelPage();
        mainMenuSteps = new MainMenuSteps();
    }

    @Step("Creating direct channel")
    public CreateChatsSteps createDirectChannel(String emailToSignIn, String userName) {
        mainMenuSteps.verifyUserCanSignIn(emailToSignIn);
        getWebDriver().quit();
        mainMenuModal
                .openPage();
        createDirectChannelPage.createDirectChannel(userName);
        return this;
    }
}
