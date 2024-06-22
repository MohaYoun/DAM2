package ut02.u03.ejs06EntrenJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class listadoUsoEspacio {
    public static void main(String[] args) throws IOException {

        // Verifica si se proporcionan argumentos (rutas de directorios)
        if (args.length == 0) {
            System.exit(1); // Salir si no hay argumentos
        }

        // Itera a través de las rutas de directorios proporcionadas como argumentos
        for (int i = 0; i < args.length; i++) {
            String[] comando = { "du", "-h", args[i] }; // Comando para obtener el uso de disco de la ruta actual

            try {
                System.out.println("Uso de espacio para el directorio: " + args[i]);

                // Configura el proceso para ejecutar el comando
                ProcessBuilder pb = new ProcessBuilder(comando);
                Process p = pb.start(); // Inicia el proceso
                p.waitFor(); // Espera a que el proceso termine

                // Lee la salida del proceso para obtener el resultado del comando
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String s = null;
                while ((s = br.readLine()) != null) {
                    System.out.println(s); // Imprime cada línea de la salida del comando
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace(); // Manejo de excepciones en caso de error
            }
        }
    }
}
