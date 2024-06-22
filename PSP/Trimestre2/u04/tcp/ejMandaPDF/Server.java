package ut02.u04.tcp.ejMandaPDF;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static String ARCHIVO = "/home/moha/PSP2324/ut02/u04/tcp/ejMandaPDF/Herrramientas_de_trabajo_git.pdf";
    private static int DATABYTE = 1024;
    public static void main(String[] args) {
        // Crea un cliente y un servidor TCP. El servidor mandará un fichero PDF a cada cliente que se conecte. 
        // El PDF estará en el disco del servidor y su ruta estará almacenada en una constante.
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                Socket socket = server.accept();
                File archivoPDF = new File(ARCHIVO);
                byte[] buffer = new byte[DATABYTE];
                FileInputStream fileInputStream = new FileInputStream(archivoPDF);
                BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());

                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                out.flush();
                fileInputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }/*
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                Socket socket = server.accept();

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                File archivopdf = new File(ARCHIVO);
                FileInputStream fileInputStream = new FileInputStream(archivopdf);

                byte[] pdf = new byte[DATABYTE];
                int bytesRead;

                while ((bytesRead = fileInputStream.read()) != -1) {
                    out.write(pdf, 0, bytesRead);
                }

                out.close();
                fileInputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
