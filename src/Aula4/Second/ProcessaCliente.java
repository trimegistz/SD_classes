package Aula4.Second;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class ProcessaCliente extends Thread
{

    Socket cliente;
    PrintWriter pw;
    DataOutputStream toServer;
    BufferedReader br;
    ListaMensagens messageList;


    public ProcessaCliente(Socket cliente, ListaMensagens messageList) throws IOException {
        this.cliente = cliente;
        br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        pw = new PrintWriter(cliente.getOutputStream(), true);
        this.messageList = messageList;
    }

        public void run(){
//        new Inbox().start();
        new Outbox().start();
    }


    public Socket getSocket() {
        return this.cliente;
    }

//    public class Inbox extends Thread{
//
//        public void run(){
//
//            String incoming="";
//            try {
//                while((incoming = br.readLine()) != null){
//                    pw.println(incoming);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    public class Outbox extends Thread{

        String message="";

        public void run() {
            try{
                while ((message = br.readLine()) != null) {
                    messageList.add(parseMessage(message));
                    if (message.equals("Bye"))
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public String parseMessage(String message){
        LocalDateTime date;
        String ip = cliente.getInetAddress().toString();
        date = java.time.LocalDateTime.now();
        String newMessage = date.getDayOfMonth() + "/" +
                date.getMonth() + "/" +
                date.getYear() + "  " +
                date.getHour() + ":" + date.getMinute() +
                "\t" + ip.substring(1) + "\t:" + message;
        return newMessage;
    }

    public void printMessages(ArrayList<String> messages) {
        Iterator<String> iterator = messages.iterator();
        while(iterator.hasNext()) {
            pw.println("New batch");
            pw.println(iterator.next() +"\n");
        }
    }

}
