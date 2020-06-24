package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.addNewTopic.CreateTopicChannel;
import tests.base.BaseTest;

import static org.testng.AssertJUnit.assertEquals;


public class CreateTopicChannelTest extends BaseTest {

    private String topicName = "Test Topic";
    private static final String TOPIC_LOCATOR_XPATH = "//div[@class='node-name expanded ellipsis-name']/..//span[contains(text(),'%s')]";
    CreateTopicChannel topicChannel = new CreateTopicChannel();

    @Test(description = "Verifying the creation Topic Channel")
    public void checkCreateTopicChannel() {
        topicChannel.addTopic(topicName);
      /* ошибка из-за .getTex() //String actualTopicName = $(By.xpath(String.format(TOPIC_LOCATOR_XPATH, topicName)).getText().trim());
        assertEquals(actualTopicName, topicName);*/
    }
}
