package ut02.u04.udp.ecoYrevertir;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SrvrUDP {
    // Tarea 2: Eco

    // Crea un programa con parámetros similares al anterior.
    // Esta vez el servidor se ejecutará con un bucle while true. Será un servicio de eco, envía al cliente la información que se le ha mandado.
    // El cliente envía información, y espera una respuesta en el mismo puerto.
    // Tarea 3: Servicio StringReverse
    
    // Implementa un servicio que devuelva la cadena recibida pero dada la vuelta
    private static final int MAX_LENGTH = 65535;

    public static void main(String args[]) {
        try {
            int puerto = Integer.parseInt(args[0]);
            DatagramSocket socket = new DatagramSocket(puerto); // Abre el socket en el puerto 9876
            byte[] receivedData = new byte[MAX_LENGTH]; // Creamos array recibidor de datos
            byte[] sendData = new byte[MAX_LENGTH]; // Creamos array enviador de datos
            InetAddress address;

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete
                puerto = receivedPacket.getPort();
                address = receivedPacket.getAddress();
                String messageRecep = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                // String messageReverse = invertirCadena(messagel); // Revertir el mensaje.
                // System.out.println("He recibido de la ip " + address.getHostAddress() + " el mensaje revertido es: " + messageReverse);
                System.out.println("He recibido de la ip " + address.getHostAddress() + " el mensaje: " + messageRecep);
                String messageSend = "Esta recepcionado, esto es un mendaje desde el servidor.";
                sendData = messageSend.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, puerto);
                socket.send(sendPacket); // Envía el paquete al servidor
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String invertirCadena(String cadena){
         
        cadena = cadena.toLowerCase();
        String cadenaInvertida = "";
         
        // Invertimos la cadena
        char caracter;
        for (int i = cadena.length() - 1; i >= 0; i--) {
            caracter = cadena.charAt(i);
            cadenaInvertida += caracter;
        }
         
        return cadenaInvertida;
    }
}
