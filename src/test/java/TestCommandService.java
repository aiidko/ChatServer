import CommandService.implementation.ExecutableCommandsContainer;
import CommandService.implementation.WhoCommand;
import CommandService.implementation.CommandService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TestCommandService {
    CommandService commandService = CommandService.getInstance();

    @Before
    public void initCommandService(){
        ExecutableCommandsContainer.registerAllCommands();
    }

    @Test
    public void TestGetCommandFromMessage(){
        assertEquals("/who",commandService.getCommandFromMessage("/who asf"));
        assertEquals("/setname",commandService.getCommandFromMessage("/setname asf"));
    }

    @Test
    public void TestIsDataCommand(){
        assertEquals(true,commandService.isDataCommand("/who"));
        assertEquals(false, commandService.isDataCommand("afsgasdg"));
    }

    @Test
    public void TestRegisterCommand(){
        int beforeRegister = commandService.getListOfCommands().size();
        commandService.registerCommand("/newcom", WhoCommand.class);
        int afterRegister = commandService.getListOfCommands().size();
        assertEquals(beforeRegister+1,afterRegister);
    }

    @Test
    public void TestGetListOfCommands(){
        assertEquals(4,commandService.getListOfCommands().size());
    }
}
