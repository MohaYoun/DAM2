package ut02.u05.http.ej2apiSumaNumero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHTTP {
    private static final int DEFAULT_PORT = 8888;
    private static final int RESOURCE_POSITION = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

        while (true) {
            // Se acepta la conexion del cliente
            Socket connCliente = server.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(connCliente.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connCliente.getOutputStream()));

            // Se le la peticion del cliente que lo escribe en la cabecera del navegador
            String cabecera = br.readLine();
            System.out.println(cabecera);

            String info, html;
            // Se extrae la informacionde la cabecera
            info = extraeInformacion(cabecera);
            html = generaPagina(info);
            
            // Escribir cabecera 
            bw.write("HTTP/1.1 200 OK\n");
            // Para especificar que el contenido sera de tipo json
            bw.write("Content-Type: application/json; charset=utf-8\n");
            bw.write("\n");
            bw.write(html);
            bw.flush();

            br.close();
            bw.close();
            connCliente.close();
        }
    }

    private static String generaPagina(String info) {
        String[] datos = info.split("/");
        String operacion = datos[1].toString(); // ej: /<suma>/
        int operando1 = Integer.parseInt(datos[2]); // ej: /suma/<568>/
        int operando2 = Integer.parseInt(datos[3]); // ej: /suma/568/<234>/
        int resultado = 0;
        if (operacion.equalsIgnoreCase("suma")) {
            resultado = operando1 + operando2;
        } else if (operacion.equalsIgnoreCase("resta")) {
            resultado = operando1 - operando2;
        } else if (operacion.equalsIgnoreCase("multiplicacion")) {
            resultado = operando1 * operando2;
        } else if (operacion.equalsIgnoreCase("division")) {
            resultado = operando1 / operando2;
        }
        // StringBuilder builder = new StringBuilder();
        // builder.append("<h2>Resultado: "+resultado+"</h2>");
        // return builder.toString();
        return String.format("{\"Resultado\": \"%s\"}", resultado);
        
    }

    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}
