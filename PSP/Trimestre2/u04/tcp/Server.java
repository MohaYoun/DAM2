package ut02.u04.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server;
		try {
			server = new ServerSocket(1234);
			while(true) {
				// Espera cliente
				// Socket socket = server.accept();
				
				// DataInputStream in = new DataInputStream ( socket.getInputStream());
				// DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				
				// String s = in.readLine();
                // System.out.print("He recibido: "+s);
				// for (int x = 0; x < 32000; x++) {
				// 	for (int i = 0; i < 1000; i++) {
				// 		out.writeUTF(x + s.toUpperCase());
				// 	}
				// }
                // in.close();
				// out.close();
				// socket.close();
                Socket socket = server.accept();
				
				DataInputStream in = new DataInputStream (socket.getInputStream());
				System.out.print("He recibido: " + in.readLine());
				
				in.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
