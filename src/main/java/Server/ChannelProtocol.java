package Server;

import Injection.ServiceContainer;
import CommandService.implementation.CommandService;
import CommandService.implementation.ExecutableCommandsContainer;
import CommandService.interfaces.ICommandService;
import Config.Config;

import MessageService.implementation.ChatHistory;
import MessageService.implementation.LoggerService;
import MessageService.implementation.MessageService;
import MessageService.interfaces.IMessageService;
import UserService.implementation.UserService;
import UserService.interfaces.IUserService;
import UserService.interfaces.IUserView;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

class ChannelProtocol implements IWritableProtocol, IProtocol {
    private Selector serverSel;

    private ServerSocketChannel serverChannel;

    private Map<Integer,SocketChannel> usersChannels;

    private IMessageService messageService;

    private IUserService userService= null;

    private ICommandService commandService;

    public void protocolInit() throws IOException {
        serverSel = Selector.open();
        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(Config.HOST, Config.PORT));
        serverChannel.register(serverSel, SelectionKey.OP_ACCEPT);
        usersChannels = new HashMap<Integer, SocketChannel>();
        ChatHistory history = new ChatHistory();
        messageService = new MessageService(history, this);
        userService = new UserService();
        ServiceContainer.registerService(messageService);
        ServiceContainer.registerService(userService);
        commandService =  CommandService.getInstance();
        ServiceContainer.registerService(CommandService.getInstance());
        ExecutableCommandsContainer.registerAllCommands();
    }

    public void protocolWork() throws Exception {
        try {
            serverSel.select();
            Iterator keys = serverSel.selectedKeys().iterator();
            while (keys.hasNext()){
                SelectionKey key = (SelectionKey) keys.next();
                keys.remove();
                if (!key.isValid())
                    continue;
                if (key.isAcceptable()){
                    addNewUser(key);
                }
                else if (key.isReadable()){
                    workWithDataFromUser(key);
                }
                else if (key.isWritable()){
                    key.interestOps(SelectionKey.OP_READ);
                }
            }
        } catch(ClosedChannelException ex){
            LoggerService.writeErrorLog("Server channel closed.ERROR!" + ex.toString());
        }
    }

    public void protocolStopWork() throws IOException {
            serverSel.close();
            serverChannel.close();
            LoggerService.writeInfoLog("Server successfully stopped.");
    }

    public void writeTo(int userId, String message){
        try {
            usersChannels.get(userId).write(ByteBuffer.wrap((message.trim() + "\n").getBytes("UTF-8")));
        } catch (IOException ex){
            LoggerService.writeErrorLog("Cant write to user." + ex.toString());
        }
    }

    private String receiveDataFromUserChannel(SocketChannel channel){
        try {
            ByteBuffer buffer = ByteBuffer.allocate(Config.MESSAGE_SIZE);
            channel.read(buffer);
            return new String(buffer.array()).trim();
        } catch (IOException ex){
            LoggerService.writeErrorLog("Cant read from user." + ex.toString());
            return null;
        }
    }

    private void workWithDataFromUser(SelectionKey key) throws InstantiationException {
        SocketChannel channel = (SocketChannel) key.channel();
        IUserView user = userService.getUser(channel.hashCode());
        if (user == null)
        {
            removeUser(user, key);
            return;
        }
        String data = receiveDataFromUserChannel(channel);
        if (data == null){
            removeUser(user, key);
            return;
        }
        if (data.startsWith("/"))
        {
            if (commandService.isDataCommand(commandService.getCommandFromMessage(data)))
                commandService.executeCommand(data, user);
            else
                messageService.sendMessageToUser(user,"No such command on server. Use /help.\n");
        }
        else {
            messageService.sendMessageFromUser(user, data, userService.getUsersOnServer());

        }
        key.interestOps(SelectionKey.OP_READ);
    }

    private void addNewUser(SelectionKey key) throws IOException {
        try {
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverChannel.accept();
            channel.configureBlocking(false);
            Integer userId = channel.hashCode();
            usersChannels.put(userId, channel);
            IUserView user = userService.addNewUser(userId);
            messageService.sendHelloMessageToNewUser(user);
            messageService.sendHistoryToNewUser(user);
            channel.register(this.serverSel, SelectionKey.OP_READ);
        } catch (NullPointerException ex){
            LoggerService.writeErrorLog("Cant open a channel." + ex.toString());
        }

    }

    private void removeUser(IUserView user, SelectionKey key){
        try {
            userService.removeUser(user);
            if (!user.getName().equals(Config.DEFAULT_USER_NAME)){
                    messageService.sendMessageToUsers(user, "User " + user.getName() + " leave the chat.whistle", true, userService.getUsersOnServer());
            }
            messageService.sendMessageToUser(user,"Server:You have been disconnected from the server\n");
            key.interestOps(SelectionKey.OP_READ);
            key.channel().close();
            key.cancel();
        } catch (IOException ex){
            LoggerService.writeErrorLog("User has left the chat without /exit." + ex.toString());
        } catch (NullPointerException e){
            try {
                key.interestOps(SelectionKey.OP_READ);
                key.channel().close();
            } catch (IOException ex){
                LoggerService.writeErrorLog("User is not found." + ex.toString());
            }
            key.cancel();
        }
    }
}
