package Aula5.Ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MulticastClientSender extends Thread{

    private BufferedReader br;
    private PrintWriter pw;
    private Socket socket;
    private String message;

    public MulticastClientSender() throws IOException {
        this.socket = new Socket("localhost", 3000);
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.pw = new PrintWriter(socket.getOutputStream(),true);

    }

    public void run(){
        while(true) {
            try {
                message = br.readLine();
                pw.println(message);

                if (message.equalsIgnoreCase("bye")) {
                    socket.close();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

