package ut02.u04.tcp.ejMandaPDF;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Cliente {
    private static int DATABYTE = 1024;

    public static void main(String[] args) throws InterruptedException {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            byte[] buffer = new byte[DATABYTE];
            InputStream inputStream = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("/home/moha/PSP2324/ut02/u04/tcp/ejMandaPDF/archivo_recibido.pdf");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, bytesRead);
            }

            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            fileOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            Socket socket = new Socket("127.0.0.1", 1234);
            byte[] pdf = new byte[DATABYTE];
            int bytesRead;
            DataInputStream in = new DataInputStream(socket.getInputStream());
            //FileOutputStream outputStream = new FileOutputStream(ARCHIVO);

            while ((bytesRead = in.read(pdf)) != -1) {
                //outputStream.write(pdf, 0, bytesRead);
            }

            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
