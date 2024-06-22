package ut02.u04.tcp.tcpThreads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClaseServerMultiThread implements Runnable{

    Socket socket;

    

    public ClaseServerMultiThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        while (true) {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                
                String s = in.readLine();
                System.out.println("He recibido:" + s);
                // for (int x = 0; x < 32000; x++) {
                // 	for (int i = 0; i < 1000; i++) {
                // 		out.writeUTF(x + s.toUpperCase());
                // 	}
                // }
                
                
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    
}
