package UserService.implementation;

import Config.Config;
import UserService.interfaces.INames;
import UserService.interfaces.IUserService;
import UserService.interfaces.IUserView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements IUserService {
    private Map<Integer, IUserView> usersOnServer;

    private INames Names;

    public UserService(){
        usersOnServer = new HashMap<Integer, IUserView>();
        Names = new ChatNames();
    }

    public Collection<IUserView> getUsersOnServer(){
        return usersOnServer.values();
    }

    public IUserView addNewUser(Integer userId){
        IUserView user = new User(userId, Config.DEFAULT_USER_NAME);
        usersOnServer.put(userId, user);
        return user;
    }

    public List<String> getUsersName(){
        return Names.getNames();
    }


    public IUserView getUser(Integer userId){
        return usersOnServer.get(userId);
    }

    public boolean isClientIdentification(IUserView user, String Name){
        if (Names.isNameFree(Name))
        {
            Names.addName(Name);
            Names.deleteName(user.getName());
            user.setName(Name);
            return true;
        }
        else
            return false;
    }

    public void removeUser(IUserView delUser){
        Names.deleteName(delUser.getName());
        usersOnServer.remove(delUser.getUserId());
    }
}
