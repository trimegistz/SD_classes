package Aula4.Second;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static ListaMensagens messageList = new ListaMensagens();
    private static ServerSocket ss = null;

    public static void main(String[] args) throws IOException {

        {
            try {
                ss = new ServerSocket(2028);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        ServerSender serverSender = new ServerSender(messageList);
        ProcessaCliente current;
        Socket clientSocket;
        serverSender.start();


        System.out.println("Reachded loop");
        while (true) {
            clientSocket = ss.accept();
            current = new ProcessaCliente(clientSocket,messageList);
            System.out.println(clientSocket.getInetAddress() + "  has connected");
            serverSender.add(current);
            current.start();
        }


    }

}
