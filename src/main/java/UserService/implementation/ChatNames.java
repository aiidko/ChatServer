package UserService.implementation;

import UserService.interfaces.INames;

import java.util.ArrayList;
import java.util.List;

public class ChatNames implements INames {
    private static List<String> Names;

    public  ChatNames(){
        Names = new ArrayList<String>();
    }

    public  void addName(String name){
        Names.add(name);
    }

    public  void deleteName(String name){
        Names.remove(name);
    }

    public  List<String> getNames(){
        return Names;
    }

    public boolean isNameFree(String name){
        return !Names.contains(name);
    }
}
