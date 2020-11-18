package Aula5.Ex2;

import java.io.IOException;


public class MulticastChatClient extends Thread{

    public static void main(String[] args) throws IOException {
        new MulticastClientReceiver().start();
        new MulticastClientSender().start();
    }
}
