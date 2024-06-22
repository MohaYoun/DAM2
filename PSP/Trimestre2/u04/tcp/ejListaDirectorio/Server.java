package ut02.u04.tcp.ejListaDirectorio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        // Crea un cliente y un servidor TCP. El servidor manda al cliente el listado 
        // del directorio /var/files/ como un String. Se puede hacer con el 
        // comando ls y ProcessBuilder o con las clases de Java para procesar ficheros y directorios.
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                Socket socket = server.accept();

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Ficheros: ");
                String[] commands = {
                        "ls",
                        "/var/"
                };
                // creating the process 1
                ProcessBuilder pb = new ProcessBuilder(commands);
                Process proceso = pb.start();
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

                String directorios;

                while ((directorios = stdInput.readLine()) != null) {
                    System.out.println("Lista de ficheros: " + directorios);
                    out.writeUTF(directorios);
                }
                out.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
