package Aula3.Ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


//Receiver keeps waiting for a message

public class Receiver extends Thread{

    private BufferedReader br;

    public Receiver(Socket socket) throws IOException {
        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run(){
        while(true) {
            try {
                System.out.println(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
