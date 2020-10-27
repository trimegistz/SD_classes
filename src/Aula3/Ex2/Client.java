package Aula3.Ex2;

import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        Socket socket = null;
        try {
            socket = new Socket("localhost", 2028);
        } catch (IOException e) {
            System.out.println("Could not get IO for the connection");
            System.exit(-1);
        }
        try {
            new Receiver(socket).start();
            new Sender(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
