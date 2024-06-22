package EjercicioRepasoJunio.Java.UDP_SistCondAutn;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteSensor implements Runnable{
    private static final String PUERTO = "9999";
    private static final int MIN_EJECUCION = 2000;
    private static final double MAX_EJECUCION = 5000;
    private int id;
    private int kilometro;

    public ClienteSensor(int id, int kilometro) {
        this.id = id;
        this.kilometro = kilometro;
    }
    public int getId() {
        return id;
    }
    
    public int getKilometro() {
        return kilometro;
    }
    @Override
    public void run() {
        try {
            DatagramSocket ds = new DatagramSocket();
            String linea = "Sensor id: " + getId() + " y kilometro :" + getKilometro();
            byte buffer[] = linea.getBytes();
            String ip = "192.168.56.101";
            int puerto = Integer.parseInt(PUERTO);
            DatagramPacket p = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(ip), puerto);
            Thread.sleep((int) (MIN_EJECUCION+(Math.random()* MAX_EJECUCION - MIN_EJECUCION)));
            ds.send(p);
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
