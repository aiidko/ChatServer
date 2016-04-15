package UserService.interfaces;

import java.util.List;

public interface INames {
    void addName(String name);

    void deleteName(String name);

    boolean isNameFree(String name);

    List<String> getNames();
}
