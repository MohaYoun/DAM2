package ut02.u03.ejs06EntrenJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class listDirIP {
    public static void main(String[] args) {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            String comando;
            if (os.contains("win")) {
                // Si es Windows, utiliza ipconfig
                comando = "ipconfig";
            } else {
                // Si no es Windows, utiliza ifconfig
                comando = "ifconfig";
            }

            // Configurar el proceso
            ProcessBuilder pb = new ProcessBuilder(comando);
            Process p = pb.start();

            // Leer la salida del proceso
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            // Expresión regular para encontrar direcciones IP
            Pattern pattern = Pattern.compile("\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b");

            // Mostrar las direcciones IP encontradas
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    System.out.println("Dirección IP encontrada: " + matcher.group());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
