package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class BotUnitTest {

    String currentDir = System.getProperty("user.dir");
    Bot bot;


    @BeforeEach
    void setUp() {
        bot = new Bot("0", "chatbotData/data.xml",  "chatbotData/faq.xml",   "chatbotData/rating.xml");
    }

    @Test
    @DisplayName("Test bot when state is 0")
    void testBotState0() {
        bot = new Bot("0", "chatbotData/data.xml",  "chatbotData/faq.xml",   "chatbotData/rating.xml");
        assertEquals( Integer.parseInt(bot.level),0);
    }
}
