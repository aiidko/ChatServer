package CommandService.implementation;

import Config.Config;
import Injection.Inject;
import CommandService.interfaces.ICommand;
import MessageService.interfaces.IMessageService;
import UserService.interfaces.IUserService;
import UserService.interfaces.IUserView;

class ExitCommand implements ICommand {

    @Inject
    private IUserService userService;

    @Inject
    private IMessageService messageService;

    static final String commandName = "/exit";

    public void execute(IUserView user ,String message){
        userService.removeUser(user);
        if (!user.getName().equals(Config.DEFAULT_USER_NAME))
            messageService.sendMessageToUsers(user, "User " + user.getName() + " leave the chat.whistle",true, userService.getUsersOnServer());
    }
}
