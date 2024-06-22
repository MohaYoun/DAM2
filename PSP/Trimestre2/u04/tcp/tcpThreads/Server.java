package ut02.u04.tcp.tcpThreads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1234);
            Socket socket = server.accept();
            ClaseServerMultiThread csm = new ClaseServerMultiThread(socket);
            Thread th1 = new Thread(csm);
            th1.start();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
