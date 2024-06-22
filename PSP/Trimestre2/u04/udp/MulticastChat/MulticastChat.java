package ut02.u04.udp.MulticastChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

public class MulticastChat {
    public static void main(String[] args) {
        try {

            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                System.out.println(netint);
            }
            Scanner in = new Scanner(System.in);
            System.out.println("Especifica el nombre del interfaz");
            String iName = in.nextLine();
            NetworkInterface netIf = NetworkInterface.getByName(iName);
            System.out.println(netIf);

            int port = 1234;

            InetAddress mcastaddr = InetAddress.getByName("230.0.0.1");
            InetSocketAddress group = new InetSocketAddress(mcastaddr, port);
            MulticastSocket s = new MulticastSocket(port);

            s.joinGroup(group, netIf);
            while (true) {
                // Codigo de escritura
                Scanner sc = new Scanner(System.in);
                System.out.println("Envia el mensaje: ");
                String msg = sc.nextLine();

                byte[] msgBytes = msg.getBytes();
                DatagramPacket dataSend = new DatagramPacket(msgBytes, msgBytes.length, group);
                s.send(dataSend);

                /* Código de lectura */
                byte[] buf = new byte[1000];
                DatagramPacket recv = new DatagramPacket(buf, buf.length);
                s.receive(recv);
                System.out.println("Mensaje recibido: " + new String(recv.getData(), 0, recv.getLength()));
            }

            // Lo dejo cuando quiera
            // s.leaveGroup(group, netIf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
