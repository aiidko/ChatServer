/*
import Server.IServer;
import Server.ChatServer;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;


public class TestServer {

    @Test
    public void TestStartServer(){
        try {
            IServer server = new ChatServer();
            assertEquals(true,server.startServer());
            server.stopServer();
        } catch (IOException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestStopServer(){
        try {
            IServer server = new ChatServer();
            server.startServer();
            assertEquals(true, server.stopServer());
        } catch (IOException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }

}
*/