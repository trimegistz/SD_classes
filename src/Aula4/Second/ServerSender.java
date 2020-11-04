package Aula4.Second;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ServerSender extends Thread{

    ArrayList<ProcessaCliente> clientList;
    ListaMensagens messageList;

    public ServerSender( ListaMensagens messageList) {
        this.messageList = messageList;
        this.clientList = new ArrayList<>();
    }

    public void add(ProcessaCliente newClient) throws IOException {
        clientList.add(newClient);
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(messageList.toString());

            ArrayList<String> messages = messageList.sendAll();
            Iterator<ProcessaCliente> clientIterator = clientList.iterator();
            while (clientIterator.hasNext()) {
                clientIterator.next().printMessages(messages);
                }
            }
        }
    }

