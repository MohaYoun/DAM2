package EjercicioRepasoJunio.Java.HTTP_musica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ServidorHTTP {
    private static final int DEFAULT_PORT = 8888;
    private static final int RESOURCE_POSITION = 1;
    private static final String PATH_MUSIC = "/home/moha/PSP2324/EjercicioRepasoJunio/Java/HTTP_musica";

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

        while (true) {
            // Se acepta la conexion del cliente
            Socket connCliente = server.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(connCliente.getInputStream()));
            OutputStream os = connCliente.getOutputStream();

            // Se lee la petición del cliente que lo escribe en la cabecera del navegador
            String cabecera = br.readLine();
            System.out.println(cabecera);

            String info;
            byte[] audioBytes;
            
            // Se extrae la información de la cabecera
            info = extraeInformacion(cabecera);
            audioBytes = obtieneCancion(info);

            if (audioBytes != null) {
                // Escribir cabecera
                os.write("HTTP/1.1 200 OK\n".getBytes());
                os.write("Content-Type: audio/mpeg\n".getBytes());
                os.write("Content-Length: ".getBytes());
                os.write(String.valueOf(audioBytes.length).getBytes());
                os.write("\n\n".getBytes());
                os.write(audioBytes);
                os.flush();
            } else {
                os.write("HTTP/1.1 404 Not Found\n".getBytes());
                os.write("\n".getBytes());
                os.write("File Not Found".getBytes());
                os.flush();
            }

            br.close();
            os.close();
            connCliente.close();
        }
    }

    private static byte[] obtieneCancion(String info) {
        byte[] audioBytes = null;
        try {
            audioBytes = Files.readAllBytes(Paths.get(PATH_MUSIC + info));
        } catch (Exception e) {
            System.out.println("No se encuentra la canción " + e.getMessage());
        }
        return audioBytes;
    }

    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}