
import MessageService.implementation.ChatHistory;
import Config.Config;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class TestChatHistory {

    @Before
    public void InitConfig(){
        new Config();
    }

    @Test
    public void TestAddMessage(){
        ChatHistory History= new ChatHistory();
        History.addMessage("asd");
        assertEquals(1,History.getHistory().size());
    }


    @Test
    public void TestAddMoreThanConfigHistoryLengthAvailableMessage(){
        ChatHistory History= new ChatHistory();
        for(int i = 0; i < Config.HISTORY_LENGTH + 50 ;i++)
            History.addMessage("asd");
        assertEquals(Config.HISTORY_LENGTH,History.getHistory().size());
    }

    @Test
    public void TestGetHistory(){
        ChatHistory History= new ChatHistory();
        History.addMessage("asd");
        Queue<String> newHistory= History.getHistory();
        assertNotNull(newHistory);
    }

}
