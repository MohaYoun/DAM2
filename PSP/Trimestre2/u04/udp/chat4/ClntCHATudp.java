package ut02.u04.udp.chat4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClntCHATudp {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        try {
            // Creamos el socket donde se mandar y recibiran los paquetes de cliente-servidor
            DatagramSocket socket = new DatagramSocket();

            // Escribimos la direccion ip del servidor.
            InetAddress serverAddress = InetAddress.getByName(args[1]);
            // Definimos las variables enviador de datos y recibir de datos que tiene que ser de tipo byte.
            byte[] sendData = new byte[MAX_LENGTH];
            byte[] receivedData = new byte[MAX_LENGTH];

            // Escribimos el puerto en el que esta el servidor.
            int puerto = Integer.parseInt(args[0]);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Ingrese un mensaje: ");
                String sentence = br.readLine();

                // Codificamos el mensaje en bytes para poder mandarlo. 
                sendData = sentence.getBytes();
                // Creamos el paquete de datagrama para enviar al servidor.
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, puerto);
                // Enviamos el paquete.
                socket.send(sendPacket);

                // Creamos el paquete de datagrama que recibe del servidor.
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                // Recibimos el paquete.
                socket.receive(receivedPacket);
                // Descodificamos el paquete a String para poder leerlo.
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Respuesta del servidor: " + message);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}