package CommandService.interfaces;


import UserService.interfaces.IUserView;

import java.util.List;

public interface ICommandService extends IService {
    void executeCommand(String keyCommand, IUserView user);

    String getCommandFromMessage(String message);

    List<String> getListOfCommands();

    boolean isDataCommand(String data);

}
