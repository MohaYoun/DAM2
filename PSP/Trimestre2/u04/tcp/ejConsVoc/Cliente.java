package ut02.u04.tcp.ejConsVoc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
         try {
            Socket socket = new Socket("127.0.0.1", 1234);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            System.out.println("Introduce mensaje: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String texto = br.readLine();
            out.writeUTF(texto);

            String s = in.readUTF();
            System.out.println(s);

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
