package ut02.u04.udp.udpEjClase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {

    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            // byte buffer[] = "Hola mundo\n".getBytes();
            String linea = args[2];
            byte buffer[] = linea.getBytes();
            // String ip = "192.168.20.200";
            String ip = args[1];
            int puerto = Integer.parseInt(args[0]);
            // ds.setBroadcast(true);
            DatagramPacket p = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(ip), puerto);

            ds.send(p);
            // ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
