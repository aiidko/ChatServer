package CommandService.implementation;

import Injection.Injector;
import CommandService.interfaces.ICommand;
import CommandService.interfaces.ICommandService;
import UserService.interfaces.IUserView;
import MessageService.implementation.LoggerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandService implements ICommandService {
    private Map<String, Class> commands;
    private static CommandService _instance;


    private CommandService(){
        commands = new HashMap<String, Class>();
    }

    public static CommandService getInstance(){
        if (CommandService._instance == null){
            CommandService._instance = new CommandService();
        }
        return CommandService._instance;
    }


    public  void registerCommand(String keyCommand, Class command){
        commands.put(keyCommand, command);
    }

    public void executeCommand(String message, IUserView user)  {
        try {
            ICommand command = (ICommand)  (commands.get(getCommandFromMessage(message))).newInstance();
            Injector.doInject(command);
            command.execute(user,message);
        } catch (IllegalAccessException ex){
            LoggerService.writeErrorLog("Can't inject filed." + ex.toString());
        } catch (NullPointerException ex){
            LoggerService.writeInfoLog("User typed command, that not existing on server." + ex.toString());
        } catch (InstantiationException ex){
            LoggerService.writeInfoLog( ex.toString());
        }
    }

    public List<String> getListOfCommands(){
        List<String> commandsList= new ArrayList<String>();
        for ( Map.Entry<String,Class> entry: commands.entrySet())
        {
            commandsList.add(entry.getKey());
        }
        return commandsList;
    }

    public String getCommandFromMessage(String message){
        int isSpace = message.indexOf(" ");
        return (isSpace>-1)? message.substring(0,message.indexOf(" ")).trim() : message.trim();
    }

    public boolean isDataCommand(String data){
        return commands.containsKey(data);
    }
}
