package ut02.u05.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.text.View;

public class ServidorHTTP {

    private static final int DEFAULT_PORT = 8765;
	private static final int RESOURCE_POSITION = 1;
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

		while (true){
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
			//writer.write("Content-Type: application/json; charset=utf-8\n");
			bw.write("\n");
			bw.write(html);
			bw.flush();

			br.close();
			bw.close();
			connCliente.close();
		}
	}

	private static String generaPagina(String info) {
		return String.format("<h1>Has pedido <b>%s</b></h1>", info);
		//return String.format("{\"cosa\": \"%s\"}", info);
	}

	private static String extraeInformacion(String header) {
		return header.split(" ")[RESOURCE_POSITION];
	}
}
