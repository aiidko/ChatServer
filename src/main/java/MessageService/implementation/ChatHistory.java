package MessageService.implementation;

import Config.Config;
import MessageService.interfaces.IHistory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatHistory implements IHistory {
    private static Queue<String> history;

    public  ChatHistory(){
        history = new ConcurrentLinkedQueue<String>();
    }

    public  void addMessage(String message){
        if (history.size() >= Config.HISTORY_LENGTH){
            history.poll();
        }
        history.add(message);
    }

    public Queue<String> getHistory(){
        return history;
    }
}
