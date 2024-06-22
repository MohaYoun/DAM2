package ut02.u04.udp.UnicastPrimo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ResponseHandler implements Runnable {

    private InetAddress serverAddress;
    private int serverPort;
    private DatagramSocket socket;

    public ResponseHandler(DatagramSocket socket, InetAddress serverAddress, int serverPort) {
        this.socket = socket;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                System.out.println("Introduce un numero: ");
                Scanner sc = new Scanner(System.in);
                String messageSent = sc.nextLine();
                byte[] sendData = messageSent.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                socket.send(sendPacket);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
