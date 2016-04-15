import Server.IServer;
        import Server.ChatServer;
        import MessageService.implementation.LoggerService;

        import java.io.IOException;

public class MainApp {
    public static void main(String[] args){

        IServer server = null;
        try {
            server = new ChatServer();
            server.startServer();
        } catch (IOException ex){
            LoggerService.writeErrorLog("Error on try to start server." + ex.toString());
        } catch (Exception ex){
            LoggerService.writeErrorLog(ex.toString());
        } finally {
            if (server != null){
                server.stopServer();
            }
        }
    }
}
