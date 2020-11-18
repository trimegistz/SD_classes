package Aula5.Ex2;

import java.net.SocketException;
import java.net.UnknownHostException;

public class ServerRunner {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        new MulticastChatServer().start();
    }
}
