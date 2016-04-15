package CommandService.implementation;

import Injection.Inject;
import CommandService.interfaces.ICommand;
import Config.Config;
import MessageService.interfaces.IMessageService;
import UserService.interfaces.IUserService;
import UserService.interfaces.IUserView;

import java.util.List;

public class WhoCommand implements ICommand {
    @Inject
    private IUserService userService;

    @Inject
    private IMessageService messageService;

    static final String commandName = "/who";

    public void execute(IUserView user , String message){
        List<String> usersName= userService.getUsersName();
        for(String name : usersName){
            if (!user.getName().equals(name) && !name.equals(Config.DEFAULT_USER_NAME))
                messageService.sendMessageToUser(user, name);
        }
    }
}
