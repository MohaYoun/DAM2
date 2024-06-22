package EjercicioRepasoJunio.Java.UDP_SistCondAutn;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServidorProcesador {
    
    private static final int MAX_LENGTH = 65535;
    private static final int PUERTO = 9999;
    private static final String LOG_FILE_PATH = "/home/moha/PSP2324/EjercicioRepasoJunio/Java/UDP_SistCondAutn/incidencias.txt";

    public static void main(String args[]) {
        try {
            // Creamos el socket donde se mandar y recibiran los paquetes de cliente-servidor 
            // y le pasamos de parametro el puerto del servidor que es donde recibira los paquetes.
            DatagramSocket socket = new DatagramSocket(PUERTO);

            // Definimos las variables enviador de datos y recibir de datos que tiene que ser de tipo byte.
            byte[] receivedData = new byte[MAX_LENGTH];
            InetAddress address;
            // Creamos el bucle while para que no se cierre la sesion del servidor.
            while (true) {
                // Creamos el paquete de datagrama que recibe del cliente.
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                // Recibimos el paquete.
                socket.receive(receivedPacket);
                // Obtenemos la direccion ip del cliente.
                // puerto = receivedPacket.getPort();
                address = receivedPacket.getAddress();
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                registrarPeticiones(message, address);
                System.out.println("He recibido de la ip " + address + " el mensaje: " + message);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void registrarPeticiones(String distacia, InetAddress address){
        try {
            FileWriter fw = new FileWriter(LOG_FILE_PATH, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String timeStap = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());

            pw.println(timeStap + " - Distancia: " + distacia + " - Identicador: " + address);
            pw.close();
        } catch (IOException ioe) {
            System.out.println("Error al escribir en el archivo de registro " + ioe.getMessage());
        }
    } 
}
