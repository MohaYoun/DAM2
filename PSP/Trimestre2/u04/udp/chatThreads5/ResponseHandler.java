package ut02.u04.udp.chatThreads5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class ResponseHandler implements Runnable { // Controlador de Respuestas

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
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String messageSent = br.readLine();
                byte[] sendData = messageSent.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}