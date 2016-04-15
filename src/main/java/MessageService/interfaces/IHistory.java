package MessageService.interfaces;

import java.util.Queue;

public interface IHistory {

    void addMessage(String message);

    Queue<String> getHistory();
}
