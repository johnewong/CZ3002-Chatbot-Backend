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
        bot =  new Bot("0", currentDir + "\\chatbotData\\data.xml", currentDir + "\\chatbotData\\faq.xml", currentDir + "\\chatbotData\\rating.xml");
    }

    @Test
    @DisplayName("Simple test")
    void testBot() {
        assertEquals(0,0);
    }
}
