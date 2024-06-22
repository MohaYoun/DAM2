package ut02.u04.udp.ecoYrevertir;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClntUDP {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            //InetAddress IPAddress = InetAddress.getByName("192.168.20.214"); // Dirección del servidor, mirarlo con ip a
            InetAddress IPAddress = InetAddress.getByName(args[1]);
            byte[] sendData = new byte[MAX_LENGTH];
            byte[] receivedData = new byte[MAX_LENGTH];
            String sentence = args[2]; // Mensaje a enviar
            int puerto = Integer.parseInt(args[0]);
            sendData = sentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puerto);

            socket.send(sendPacket); // Envia el paquete al servidor

            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            socket.receive(receivedPacket); // Espera y recibe el paquete

            // Extrae la informacion del paquete
            String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            System.out.println("Mensaje recibido: " + message);
            
            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
