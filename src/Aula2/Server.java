package Aula2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        KnockKnockProtocol knock = new KnockKnockProtocol();
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(1025);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't get IO for the connection to port 1025.");
            System.exit(1);
        }

        Socket clientSocket = null;
        String  fromClient = "";
        String fromServer = "";
        BufferedReader in = null;

        try {
            clientSocket = ss.accept();
            System.out.println("Connected to client");
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),true);

            output.println("Knock Knock");
            while (!fromServer.equals("Bye.")) {
                fromClient = in.readLine();
                fromServer = knock.processInput(fromClient);
                output.println(fromServer);
            }
            ss.close();

        } catch (IOException e) {
            System.out.println("Accept failed");
            System.exit(1);

        }
        in.close();
    }
}
