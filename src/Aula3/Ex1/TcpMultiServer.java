package Aula3.Ex1;

import java.io.IOException;
import java.net.ServerSocket;

//To test, on terminal enter: telnet localhost 2048


public class TcpMultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int port = 2048;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+ port +".");
            System.exit(-1);
        }

        while (listening) {
            System.out.println("Waiting on port: " + port);
            new WorkerThread(serverSocket.accept()).start();
        }
        serverSocket.close();
    }
}