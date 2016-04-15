package CommandService.implementation;


public class ExecutableCommandsContainer {
        public static void registerAllCommands(){
            CommandService commandService = CommandService.getInstance();
            commandService.registerCommand(WhoCommand.commandName, WhoCommand.class);
            commandService.registerCommand(ExitCommand.commandName, ExitCommand.class);
            commandService.registerCommand(SetNameCommand.commandName, SetNameCommand.class);
            commandService.registerCommand(HelpCommand.commandName, HelpCommand.class);
        }
}
