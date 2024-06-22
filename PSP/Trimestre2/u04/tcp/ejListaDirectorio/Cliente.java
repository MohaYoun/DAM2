package ut02.u04.tcp.ejListaDirectorio;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String s = "";
            try {
                while (s != null) {
                    s = in.readUTF();
                    System.out.println(s);
                }
            } catch (EOFException e) {
                System.out.println("Se ha cerrado la conexión del servidor");
            }

            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
