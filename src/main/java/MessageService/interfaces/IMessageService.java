package MessageService.interfaces;

import CommandService.interfaces.IService;
import UserService.interfaces.IUserView;

import java.util.Collection;

public interface IMessageService  extends IService {
    void sendMessageToUser(IUserView user, String message);

    void sendMessageToUsers(IUserView user, String message, boolean isServerInfo, Collection<IUserView> usersOnServer);

    void sendHelloMessageToNewUser(IUserView user);

    void sendHistoryToNewUser(IUserView user);

    void sendMessageFromUser(IUserView user, String message, Collection<IUserView> usersOnServer);

}
