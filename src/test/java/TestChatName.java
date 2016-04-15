import UserService.implementation.ChatNames;
import UserService.interfaces.INames;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestChatName {

    @Test
    public void TestAddName(){
        ChatNames Names= new ChatNames();
        Names.addName("asd");
        assertEquals(1,Names.getNames().size());
    }

    @Test
    public void TestDeleteName(){
        ChatNames Names= new ChatNames();
        Names.addName("asd");
        Names.addName("asd1");
        Names.deleteName("asd");
        assertEquals(1, Names.getNames().size());
    }

    @Test
    public void TestisNameFree(){
        INames Names= new ChatNames();
        Names.addName("asd");
        Names.addName("asd1");
        assertEquals(true, Names.isNameFree("name"));
        assertEquals(false, Names.isNameFree("asd1"));
    }

}
