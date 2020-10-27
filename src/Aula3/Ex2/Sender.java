package Aula3.Ex2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


//Sender keeps waiting for a message to send

public class Sender extends Thread{

    private PrintWriter pw;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Sender(Socket socket) throws IOException {
        this.pw = new PrintWriter(socket.getOutputStream(),true);
    }

    public void run(){
        System.out.println("RUNNING");
        while(true) {
            try {
                pw.println( br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
