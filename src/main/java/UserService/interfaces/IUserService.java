package UserService.interfaces;

import CommandService.interfaces.IService;

import java.util.Collection;
import java.util.List;

public interface IUserService extends IService {
    void removeUser(IUserView delUser);

    boolean isClientIdentification(IUserView user, String name);

    List<String> getUsersName();

    IUserView getUser(Integer userId);

    Collection<IUserView> getUsersOnServer();

    IUserView addNewUser(Integer userId);
}
