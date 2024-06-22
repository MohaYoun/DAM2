package ut01.u03.ejs06EntrenJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class monitorMemoria {
    public static void main(String[] args) throws IOException {
        while (true) {
            try {
                // Comando a ejecutar
                String comando[] = {"free", "-h"};

                // Configurar el proceso
                ProcessBuilder pb = new ProcessBuilder(comando);
                Process p = pb.start();

                // Leer la salida del proceso
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

                // Esperar 5 segundos antes de ejecutar el próximo comando
                Thread.sleep(5000);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
