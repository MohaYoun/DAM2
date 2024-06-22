package ut02.u04.udp.udpEjClase;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerUDP {
    private static final int MAX_LENGTH = 65535;

    public static void main(String args[]) {
        try {
            DatagramSocket ds = new DatagramSocket(Integer.parseInt(args[0]), InetAddress.getByName(args[1]));
            // DatagramSocket ds = new DatagramSocket(8888, InetAddress.getByName("127.0.0.1"));
            byte[] buffer = new byte[MAX_LENGTH];

            DatagramPacket p = new DatagramPacket(buffer, MAX_LENGTH);

            ds.receive(p);
            System.out.println(new String(p.getData(), 0, p.getLength()));

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
