package CommandService.interfaces;

import UserService.interfaces.IUserView;

public interface ICommand {
      void execute(IUserView user, String message);
}
