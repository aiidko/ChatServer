package Server;

public interface IWritableProtocol {
    void writeTo(int userId, String message);
}
