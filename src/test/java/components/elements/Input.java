package components.elements;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Input {

    public static void writeTextXpath(String field, String text){
        if (StringUtils.isNotEmpty(text)) {
            log.info("Filling in field '" + field + "' with text: " + text);
            $(By.xpath(field)).clear();
            $(By.xpath(field)).sendKeys(text);
        }
    }

    public static void writeTextCss(String field, String text){
        if (StringUtils.isNotEmpty(text)) {
            log.info("Filling in field '" + field + "' with text: " + text);
            $(field).clear();
            $(field).sendKeys(text);
        }
    }
}
