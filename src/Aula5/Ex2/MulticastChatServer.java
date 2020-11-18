package Aula5.Ex2;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.nio.Buffer;

public class MulticastChatServer extends Thread{

    private static byte[] buf;
    private static DatagramSocket datagramSocket;

    public MulticastChatServer() throws SocketException, UnknownHostException {
        buf = new byte[256];
        datagramSocket = new DatagramSocket(4445);
    }

    public void run()  {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            Socket socket;

            while (true) {
                socket = serverSocket.accept();
                System.out.println("New client connected");
                new MulticastChatServerListener(socket).start();
            }
        }catch (IOException exception) {}
    }

    public static void sendAll(String message) throws IOException {

        buf = message.getBytes();
        InetAddress group = InetAddress.getByName("230.0.0.1");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
        datagramSocket.send(packet);
    }

}
