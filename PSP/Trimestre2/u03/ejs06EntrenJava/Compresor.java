package ut01.u03.ejs06EntrenJava;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Compresor
 */
public class Compresor {

    public static void main(String[] args) throws IOException {
        // creating list of commands
        for (String dir : args) {
            // Obtener la fecha actual en el formato YYYY_MM_DD
            String date = LocalDate.now().toString();

            // Crear el nombre del archivo comprimido con el formato "directorio_fecha.tar"
            String fileName = dir.replace("/", "_").replace("\\", "_") + "_" + date + ".tar";

            // Crear lista de comandos para comprimir el directorio en un archivo tar
            List<String> commands = new ArrayList<>();
            commands.add("tar");
            commands.add("-cvf");
            commands.add(fileName);
            commands.add(dir);

            // Crear el proceso y ejecutar los comandos
            ProcessBuilder pb = new ProcessBuilder(commands);
            Process process = pb.start();

            try {
                // Leer la salida del proceso
                BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String linea = null;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // moha@moha-VirtualBox:~/PSP2324/ut01/02/ejs06EntrenJava$ javac Compresor.java 
            // moha@moha-VirtualBox:~/PSP2324/ut01/02/ejs06EntrenJava$ java Compresor /home/moha/PSP2324/ut01/u03
        }
    }
}