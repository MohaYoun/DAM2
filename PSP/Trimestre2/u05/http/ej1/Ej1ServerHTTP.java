package ut02.u05.http.ej1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Ej1ServerHTTP {
    private static final int DEFAULT_PORT = 8765;
    private static final int RESOURCE_POSITION = 1;
    private static final String DIRECCION = "/home/moha/Documents/";

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

        while (true) {
            try {
                Socket connCliente = server.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(connCliente.getInputStream()));
                String header = br.readLine();
                System.out.println(header);
                // GET ________ HTTP/1.1
                String info = extraeInformacion(header);
                String html = generaPagina(info);

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connCliente.getOutputStream()));
                // Escribir cabecera
                bw.write("HTTP/1.1 200 OK\n");
                // writer.write("Content-Type: application/json; charset=utf-8\n");
                bw.write("\n");
                bw.write(html);
                bw.flush();

                br.close();
                bw.close();
                connCliente.close();
            } catch (SocketException e) {
                System.out.println("Error garrafal.");
            }
        }
    }

    private static String generaPagina(String info) throws IOException {
        String web = DIRECCION + info;
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(web));

            String line = "";

            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fin de lectura.");
        }
        return builder.toString();
    }

    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}
