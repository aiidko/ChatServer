package CommandService.implementation;

import Injection.Inject;
import CommandService.interfaces.ICommand;
import CommandService.interfaces.ICommandService;
import MessageService.interfaces.IMessageService;
import UserService.interfaces.IUserView;

import java.util.List;

class HelpCommand implements ICommand {

    @Inject
    private IMessageService messageService;

    @Inject
    private ICommandService commandService;

    static final String commandName = "/help";

    public void execute(IUserView user, String message){
        List<String> commandList= commandService.getListOfCommands();
        messageService.sendMessageToUser(user,"Here is list of command on the server:\n");
        for(String commandName: commandList){
            messageService.sendMessageToUser(user,commandName);
        }
    }
}
