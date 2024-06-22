package ej3refuerzo;

import java.io.IOException;

public class GenerarDatos {
    public static void main(String[] args) {
        int numeroDatos = Integer.parseInt(args[1]);
        if (numeroDatos > 0) {
            for (int i = 1; i <= numeroDatos; i++) {
                // String n_archivo = "datos" + i + ".txt";
                // tienes que hacer una clase java que reciba un numero y genere las
                // combinaciones de letras de ese numero
                // Y ejecutas java Clase N || echo > archivoNum.txt
                String[] commands = {
                        // comando para ejecutar la clase java
                        "java", ".ej3refuerzo.EscribirDatos", Integer.toString(i)// ,
                        // redirigir el system.out.print
                        // ">",
                        // nombre del archivo donde va
                        // n_archivo
                };

                ProcessBuilder pb = new ProcessBuilder(commands);
                pb.inheritIO();
                try {
                    /* Process proceso = */pb.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
