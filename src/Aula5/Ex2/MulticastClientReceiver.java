package Aula5.Ex2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastClientReceiver extends Thread{

    private MulticastSocket socket;

    public MulticastClientReceiver() throws IOException {
        socket = new MulticastSocket(4446);
    }

    public void run() {
        try {

            DatagramPacket packet;
            InetAddress address = null;
            address = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(address);

            while(true) {
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
                if (received.equalsIgnoreCase("bye")) {
                    socket.leaveGroup(address);
                    socket.close();
                    break;
                }
            }
        }catch (IOException e) { e.printStackTrace(); }
    }
}
