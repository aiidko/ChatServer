package Server;

import Config.Config;
import MessageService.implementation.LoggerService;

import java.io.IOException;


public class ChatServer implements IServer {
    private IProtocol serverProtocol;

    public ChatServer() throws IOException, IllegalAccessException {
        serverProtocol = new ChannelProtocol();
        new Config();
    }

    private void listenServer() throws Exception {
        serverProtocol.protocolWork();
    }

    public void startServer() throws Exception {
        try {
            serverProtocol.protocolInit();
            LoggerService.writeInfoLog("Start the server.");
            LoggerService.writeInfoLog("Working server.");
            while (true){
                listenServer();
            }
        } catch (IOException ex){
            LoggerService.writeErrorLog("Error on try to start the server."+ ex.toString());
        }
    }

    public boolean stopServer(){
        try {
            serverProtocol.protocolStopWork();
            return true;
        } catch (IOException ex){
            LoggerService.writeErrorLog("Error on try to stop the server." + ex.toString());
            return false;
        }
    }

}





