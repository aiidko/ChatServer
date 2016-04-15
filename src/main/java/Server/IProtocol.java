package Server;

import java.io.IOException;

interface IProtocol {

    void protocolInit() throws IOException;

    void protocolWork() throws Exception;

    void protocolStopWork() throws IOException ;

}
