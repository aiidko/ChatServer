package UserService.implementation;

import UserService.interfaces.IUserView;

public class User  implements IUserView {
    private Integer userId;
    private String name;

    public User (Integer userId,String name){
        this.userId = userId;
        this.name = name;
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getUserId(){
        return userId;
    }

}
