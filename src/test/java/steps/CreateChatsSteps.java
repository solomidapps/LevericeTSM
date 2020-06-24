package steps;

import pages.addNewTopic.CreateTopicChannelPage;
import pages.createDirectChannelPage.CreateDirectChannelPage;
import pages.mainMenuPage.*;

public class CreateChatsSteps {
    private MainMenuModal mainMenuModal;
    private AddAccountModal addAccountModal;
    private JoinByTokenModal joinByTokenModal;
    private VerifyAccountModal verifyAccountModal;
    private IntroduceModal introduceModal;
    private CreateWorkspaceModal createWorkspaceModal;
    private CreateTopicChannelPage topicChannel;
    private CreateDirectChannelPage createDirectChannelPage;

    public CreateChatsSteps() {
        mainMenuModal = new MainMenuModal();
        addAccountModal = new AddAccountModal();
        joinByTokenModal = new JoinByTokenModal();
        verifyAccountModal = new VerifyAccountModal();
        introduceModal = new IntroduceModal();
        createWorkspaceModal = new CreateWorkspaceModal();
        topicChannel = new CreateTopicChannelPage();
        createDirectChannelPage = new CreateDirectChannelPage();
    }

    public CreateChatsSteps createDirectChannel() {

        return this;
    }
}
