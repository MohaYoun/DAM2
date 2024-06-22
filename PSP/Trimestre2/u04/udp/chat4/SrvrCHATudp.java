package ut02.u04.udp.chat4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class SrvrCHATudp {
        // Tarea 4: Chat UDP (envío-recepción alternativo)
        // Implementa un chat cliente-servidor, estos programas serán monothread.
        // Para facilitar la tarea primero envía el cliente, y espera respuesta.
        // Para facilitar la tarea primero espera respuesta el servidor, y luego envía respuesta.
        // Tanto el cliente como el servidor reciben como parámetro el puerto. El cliente también recibirá como parámetro la dirección ip.
        // Ambos programas muestran un prompt al usuario para pedir y mostrar los mensajes enviados.
    private static final int MAX_LENGTH = 65535;

    public static void main(String args[]) {
        try {
            // Regogemos por parametro el puerto donde va a escuchar el servidor.
            int puerto = Integer.parseInt(args[0]);
            // Creamos el socket donde se mandar y recibiran los paquetes de cliente-servidor 
            // y le pasamos de parametro el puerto del servidor que es donde recibira los paquetes.
            DatagramSocket socket = new DatagramSocket(puerto);

            // Definimos las variables enviador de datos y recibir de datos que tiene que ser de tipo byte.
            byte[] receivedData = new byte[MAX_LENGTH];
            byte[] sendData = new byte[MAX_LENGTH];
            InetAddress address;
            // Creamos el bucle while para que no se cierre la sesion del servidor.
            while (true) {
                // Creamos el paquete de datagrama que recibe del cliente.
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                // Recibimos el paquete.
                socket.receive(receivedPacket);
                // Obtenemos la direccion ip del cliente.
                puerto = receivedPacket.getPort();
                address = receivedPacket.getAddress();

                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("He recibido de la ip " + address + " el mensaje: " + message);
                // Envía una respuesta al cliente
                String mensajeServidor = "Soy el Servidor y te doy las gracias por escribirme";
                sendData = mensajeServidor.getBytes();
                // Creamos el paquete de datagrama que envia al cliente.
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, puerto);
                String line = new String(sendPacket.getData(), 0, sendPacket.getLength());
                System.out.println("Respuesta a enviar del servidor: " + line);
                socket.send(sendPacket); // Envía el paquete al servidor
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
