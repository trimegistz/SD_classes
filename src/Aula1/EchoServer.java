package Aula1;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = null;
        try {
             ss = new ServerSocket(1025);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't get IO for the connection to port 1025.");
            System.exit(1);
        }

        Socket clientSocket = null;
        String  str = null;
        BufferedReader in = null;
        
        while(true) {
        	try {
	        	    clientSocket = ss.accept();
	        	    System.out.println("Connected to client");
	        	    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        	    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(),true);
	        	    while ((str = in.readLine()) != null) {
	        	        System.out.println("message= " + str);
	        	        output.println(str);
	        	    }
	        	    ss.close();
	
	        	} catch (IOException e) {
	        	    System.out.println("Accept failed");
	        	    System.exit(1);
	
        	}
        	in.close();
    	}
    }
}
