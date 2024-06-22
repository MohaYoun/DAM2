package ut02.u03.thread02;

public class principal {
    public static void main(String[] args) {
        // Numeros n1 = new Numeros();

        // Thread t1 = new Thread(n1);
        // t1.run();
        int N = 6; // Número de Threads a crear

        Thread[] th = new Thread[N];

        for (int i = 0; i < N; i++) {
            // Crea un objeto Numeros con el número correspondiente
            th[i] = new Thread(new Numeros(i + 1));
            th[i].setName("Hilo-" + (i + 1)); // Asigna un nombre al thread para que no salga esto 'Thread[Hilo-2,5,main]:'
        }
        for (int i = 0; i < N; i++) {
            th[i].start(); // Inicia el thread
        }

        // Espera a que todos los threads terminen
        for (int i = 0; i < N; i++) {
            try {
                th[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*
         * Primero, compila tu programa Java utilizando el siguiente comando en la terminal:
         * Abrir antes la carpeta en la terminal integrada
            -javac principal.java Numeros.java 

            Después, ejecuta el programa y redirecciona la salida a un archivo:
            -java -cp /home/moha/.config/Code/User/workspaceStorage/e50f087c33e84bb489f168ea9d0b60d2/redhat.java/jdt_ws/PSP2324_4100a75d/bin ut01.u03.thread02.principal > salida.txt

            Finalmente, puedes utilizar el comando sort para verificar las tablas escritas en el archivo:
            -sort salida.txt
         */
    }
}
