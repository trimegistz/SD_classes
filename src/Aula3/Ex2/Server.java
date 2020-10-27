package Aula3.Ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Server {

    public static ArrayList<Client> clientList = new ArrayList<>();

    public static class Client{
        public Socket socket;
        public PrintWriter pw;
        public BufferedReader br;

        public Client(Socket socket) throws IOException {
            this.socket = socket;
            this.pw = new PrintWriter(socket.getOutputStream(),true);
            this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        public BufferedReader getBr() { return this.br;}
        public PrintWriter getPw() { return this.pw;}
        public Socket getSocket() { return this.socket; }

    }

    public static void main(String[] args) throws IOException {
        ServerSocket ss = null;
        Socket clientSocket;

        try{
            ss = new ServerSocket(2028);
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't get IO for the connection to port.");
            System.exit(1);
        }

        Client currentClient;

        while(true) {
          clientSocket = ss.accept();
          System.out.println(clientSocket.getInetAddress() + "  has connected");
          currentClient = new Client(clientSocket);
          clientList.add(currentClient);
          new catchMessage(currentClient).start();
        }

    }

    public static class catchMessage extends Thread{

        private BufferedReader br;
        private String message;
        private String ip;
        private Client client;
        LocalDateTime date;

        public catchMessage(Client client) {
            this.client = client;
            this.br = client.getBr();
            this.ip = client.getSocket().getInetAddress().toString();
        }

        public void run() {
            while(true) {
                try {
                    date = java.time.LocalDateTime.now();
                    message = date.getDayOfMonth() + "/" +
                            date.getMonth() + "/" +
                            date.getYear() + "  " +
                            date.getHour() + ":" + date.getMinute() +
                            "\t" + this.ip.substring(1) + "\t:" + br.readLine();
                    sendToAll(message, client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //Sends message to every client
    public static void sendToAll(String message, Client client){
        Iterator<Client> iterator = clientList.iterator();
        Client current;
        while (iterator.hasNext()) {
            current = iterator.next();
            if (message != null && !current.equals(client)) {
                current.getPw().println(message);
            }
        }
    }
}
