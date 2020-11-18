package Aula5.Ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MulticastChatServerListener extends Thread {

    private BufferedReader in;
    private String message;

    public MulticastChatServerListener(Socket socket) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;
    }

    public void run(){
        try{
            while (((message = in.readLine()) != null)){
                System.out.println(message);
                MulticastChatServer.sendAll(message);
                if(message.equalsIgnoreCase("bye")){
                    in.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
