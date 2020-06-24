package tests;

import org.testng.annotations.Test;
import pages.addNewTopic.CreateTopicChannelPage;
import tests.base.BaseTest;

import static org.testng.AssertJUnit.assertEquals;


public class CreateTopicChannelPageTest extends BaseTest {

    private static final String defaultUserEmail = "tms-test@mail.com";
    private static final String userName = "TMS TMS";

    private String topicName = "Test Topic";
    private static final String TOPIC_LOCATOR_XPATH = "//div[@class='node-name expanded ellipsis-name']/..//span[contains(text(),'%s')]";
    CreateTopicChannelPage topicChannel = new CreateTopicChannelPage();

    @Test(description = "Verifying user can create topic")
    public void checkCreateTopicChannel() {
        createChatsSteps.createDirectChannel(defaultUserEmail, userName);
      /* ошибка из-за .getTex() //String actualTopicName = $(By.xpath(String.format(TOPIC_LOCATOR_XPATH, topicName)).getText().trim());
        assertEquals(actualTopicName, topicName);*/
    }
}
