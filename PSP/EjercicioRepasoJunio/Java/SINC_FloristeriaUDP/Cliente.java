package EjercicioRepasoJunio.Java.SINC_FloristeriaUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente {

    private static final String PUERTO = "9999";
    private static final int MIN_EJECUCION = 10000;
    private static final double MAX_EJECUCION = 20000;
    private static final double MAX_CANT_COMPRA = 10;
    private static final int MAX_LENGTH = 65535;
    // private int id;
    // private Floristeria almacen;

    // public Cliente(int id, Floristeria almacen) {
    //     this.id = id;
    //     this.almacen = almacen;
    // }
    
    // public int getId() {
    //     return id;
    // }

    // public Floristeria getAlmacen() {
    //     return almacen;
    // }

    public static void main(String[] args) {
        try {
            int cantFlores = (int) (Math.random()*MAX_CANT_COMPRA);

            DatagramSocket ds = new DatagramSocket();
            String linea = "Hola quiero comprar la cantidad de flores " + cantFlores;
            byte buffer[] = linea.getBytes();
            byte[] receivedData = new byte[MAX_LENGTH];
            String ip = "192.168.56.101";
            int puerto = Integer.parseInt(PUERTO);

            DatagramPacket sendPacket = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(ip), puerto);
            // Enviamos el paquete.
            ds.send(sendPacket);

            // Creamos el paquete de datagrama que recibe del servidor.
            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            // Recibimos el paquete.
            ds.receive(receivedPacket);
            // Descodificamos el paquete a String para poder leerlo.
            String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            System.out.println("Respuesta del servidor: " + message);
            Thread.sleep((int) (MIN_EJECUCION+(Math.random()* MAX_EJECUCION - MIN_EJECUCION)));
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
