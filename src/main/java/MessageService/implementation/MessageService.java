package MessageService.implementation;

import Config.Config;
import MessageService.interfaces.IHistory;
import MessageService.interfaces.IMessageService;
import Server.IWritableProtocol;
import UserService.interfaces.IUserView;

import java.util.Collection;

public class MessageService implements IMessageService {
    private IHistory history;
    private IWritableProtocol protocol;

    public MessageService(IHistory history, IWritableProtocol protocol){
        this.history = history;
        this.protocol = protocol;
    }

    public void sendHelloMessageToNewUser(IUserView user){
        protocol.writeTo(user.getUserId(), "Welcome.Please enter a nickname using \"/setname\", otherwise you will be " +
                Config.DEFAULT_USER_NAME + ".\n You can type \"/help\" to get list of commands\n");
    }

    public void sendHistoryToNewUser(IUserView user){
        Integer userId = user.getUserId();
        for (String mes : history.getHistory())
            protocol.writeTo(userId, "History:" + mes.trim() + "\n");
    }

    public void sendMessageToUsers(IUserView user, String message, boolean isServerInfo, Collection<IUserView> usersOnServer){
        String hostName = (isServerInfo) ? "server" : user.getName();
        message = String.format("%s:%s", hostName, message);
        for (IUserView userTo  : usersOnServer){
            if (!user.equals(userTo)){
                protocol.writeTo(userTo.getUserId(), message.trim()+ "\n");
            }
        }
    }

    public void sendMessageToUser(IUserView user, String message){
            protocol.writeTo(user.getUserId(), message.trim()+ "\n");
    }

    public void sendMessageFromUser(IUserView user, String message, Collection<IUserView> usersOnServer){
            history.addMessage(message);
            sendMessageToUsers(user, message, false, usersOnServer);
    }

}
