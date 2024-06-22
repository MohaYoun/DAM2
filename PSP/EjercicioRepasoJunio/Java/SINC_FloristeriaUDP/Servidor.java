package EjercicioRepasoJunio.Java.SINC_FloristeriaUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {

    private static final int MAX_LENGTH = 65535;
    private static final int PUERTO = 9999;

    public static void main(String args[]) {
        Floristeria floristeria = new Floristeria(30);

        Thread th1 = new Thread(new Empleado(floristeria, 1));
        Thread th2 = new Thread(new Empleado(floristeria, 2));
        Thread th3 = new Thread(new Empleado(floristeria, 3));

        th1.start();
        th2.start();
        th3.start();
        try {
            // Creamos el socket donde se mandar y recibiran los paquetes de cliente-servidor 
            // y le pasamos de parametro el puerto del servidor que es donde recibira los paquetes.
            DatagramSocket socket = new DatagramSocket(PUERTO);

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
                int puerto = receivedPacket.getPort();
                address = receivedPacket.getAddress();
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("He recibido de la ip " + address + " el pedido: " + message);
                String pedido[] = message.split(" ");
                int numPedido = Integer.parseInt(pedido[pedido.length-1]);
  
                String mensajeServidor = "";
                if (floristeria.almacen.size() >= numPedido) {
                    for (int i = 0; i < numPedido; i++) {
                        floristeria.extraer();
                    }                    
                    mensajeServidor = "Soy el Servidor, tu compra ha sido realizada con exito toma tu cantidad de flores " + numPedido;
                } else {
                    mensajeServidor = "Soy el Servidor, tu compra no ha podido procesarse por falta de stock lo sentimos " + -1;
                }
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
        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
}
