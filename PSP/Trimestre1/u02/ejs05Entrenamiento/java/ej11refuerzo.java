import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ej11refuerzo {
    public static void main(String[] args) {
        try {
            // Comando para obtener información de red
            String comando = "ipconfig /all"; // o "ip a" como alternativa

            // Crear un objeto ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder(comando);

            // Iniciar el proceso
            Process p = pb.start();

            // Obtener el flujo de entrada del proceso
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            // Leer la salida del comando línea por línea
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar a que el proceso termine
            int exitCode = p.waitFor();
            System.out.println("Código de salida: " + exitCode);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
