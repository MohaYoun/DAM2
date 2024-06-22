package ut02.u04.tcp.ejConsVoc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                Socket socket = server.accept();

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                String textoRecibido = in.readUTF();
                System.out.println(textoRecibido);
                int contadorVocales = 0;
                int contadorConsonantes = 0;
                for (int i = 0; i < textoRecibido.length(); i++) {
                    if (textoRecibido.toLowerCase().charAt(i) == 'a' || textoRecibido.toLowerCase().charAt(i) == 'e'
                            || textoRecibido.toLowerCase().charAt(i) == 'i' || textoRecibido.toLowerCase().charAt(i) == 'o'
                            || textoRecibido.toLowerCase().charAt(i) == 'u') {
                        contadorVocales++;
                    }else if (textoRecibido.charAt(i) == ' ') {
                        
                    }else{
                        contadorConsonantes++;
                    }
                }

                out.writeUTF("Numero de vocales: " + contadorVocales + "\n" + "Numero de consonantes: "
                        + contadorConsonantes);
                in.close();
                out.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
