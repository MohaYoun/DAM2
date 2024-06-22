package 

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscribirDatos {
    /*
     * 3. Crea un programa que reciba por parámetro un número entero positivo n.
     * Este número indicará el número de hijos. Cada hijo generará un fichero con la
     * posibles combinación de caracteres de esa longitud.
     * El hijo 1 una letra, el hijo 2 dos letras 'aa' a la 'zz', etc. Los nombres
     * serán datos1.txt, datos2.txt, etc.
     */
    public static final char[] LETRAS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public static void main(String[] args) throws IOException {
        int longitud_combinacion = Integer.parseInt(args[0]);// recibe por parametro la longitud de las combinaciones
        String n_archivo = "datos" + longitud_combinacion + ".txt";// creamos el nombre del archivo
        ArrayList<String> listaCombinaciones = new ArrayList<>();// creamos un arraylist para guardar las combinaciones
        int contador = 1;// contador auxiliar
        while (contador <= longitud_combinacion) {
            rellenarLista(listaCombinaciones);// funcion rellenar lista
            contador++;
        }
        listaCombinaciones.sort((arg0, arg1) -> arg0.compareTo(arg1));
        /*
         * listaCombinaciones.sort(new Comparator<String>() { //sin lambda
         * public int compare(String arg0, String arg1) {
         * return 0;
         * };
         * });
         */
        FileWriter escritor = null;
        try {
            escritor = new FileWriter(n_archivo);
            for (String combinacion : listaCombinaciones) {
                escritor.append(combinacion + "\n");
                // System.out.println(combinacion);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        if (escritor != null) {
            try {
                escritor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void rellenarLista(ArrayList<String> listaCombinaciones) {
        if (listaCombinaciones.isEmpty()) {
            for (int i = 0; i < LETRAS.length; i++) {
                listaCombinaciones.add("" + LETRAS[i]);
            }
        } else {
            ArrayList<String> intermedia = new ArrayList<>(listaCombinaciones);
            for (String combinacion : intermedia) {
                listaCombinaciones.remove(combinacion);
                for (int i = 0; i < LETRAS.length; i++) {
                    listaCombinaciones.add(combinacion + LETRAS[i]);
                }
            }
        }
    }
}
/*
 * /usr/bin/env /usr/lib/jvm/java-11-openjdk-amd64/bin/java -cp
 * /home/goku/.config/Code/User/workspaceStorage/
 * be7bcf7b356e255848cdffb53811f9da/redhat.java/jdt_ws/PSP_62cdc44a/bin
 * unidad01.entrenamientoC.ejercicio3.EscribirDatos
 */