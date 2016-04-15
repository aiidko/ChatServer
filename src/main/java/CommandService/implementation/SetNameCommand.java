package CommandService.implementation;

import Injection.Inject;
import CommandService.interfaces.ICommand;
import MessageService.interfaces.IMessageService;
import UserService.interfaces.IUserService;
import UserService.interfaces.IUserView;

class SetNameCommand implements ICommand {
    @Inject
    private IUserService userService;

    @Inject
    private IMessageService messageService;

    static final String commandName = "/setname";

    public void execute(IUserView user, String message)  {
        if (!message.contains(" "))
            messageService.sendMessageToUser(user, "You forgot to enter a name.\n");
        else {
            String name = message.substring(message.indexOf(" ") + 1, message.trim().length());
            String oldName = user.getName();
            if (userService.isClientIdentification(user, name))
                messageService.sendMessageToUsers(user, oldName + " became " + name + ".\n", true, userService.getUsersOnServer());
            else
                messageService.sendMessageToUser(user, "Name taken, choose another.\n");
        }
    }
}
