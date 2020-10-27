package Aula2;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("localhost", 1025);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't get IO for the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
        String userInput;
        String answer = "";

        while (!answer.equals("Bye.")) {
            answer = in.readLine();
            System.out.println(answer);
            userInput = stdIn.readLine();
            out.println(userInput);
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }

}
