package ut02.u03.carrera;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {
    public static final int KM_RECORRER = 100;
    public static final int START_DORSAL = 1;

    public static void main(String[] args) {
        Corredor c1 = new Corredor(KM_RECORRER, 25);
        Corredor c2 = new Corredor(KM_RECORRER, 11);
    // Se crea un nuevo hilo (Thread) llamado t1 y se le pasa el 
    // objeto c1 (instancia de Corredor) como argumento al constructor del hilo.
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);

        System.out.println("La carrera ya va a comenzar");
    // Se inicia la ejecución del hilo t1, lo que llevará a cabo el método run() de la clase Corredor asociada.
        t1.start();
        t2.start();

        try {
        // Se utiliza un bloque try-catch para esperar a que ambos hilos finalicen su ejecución
        // antes de continuar con el resto del código. Esto se logra mediante el uso de join(),
        // que bloquea el hilo actual hasta que el hilo en el que se llama haya terminado.
            t1.join();
            t2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println("La carrera ha terminado");

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // System.out.println("Introduce el numero de corredores.");
        // String linea = "";
        // try {
        //     linea = br.readLine();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // int numCor = Integer.parseInt(linea);
        
        // Thread corredores [] = new Thread[numCor];

        // for(int i = 0; i< numCor; i++){
        //     corredores[i] = new Thread(new Corredor(KM_RECORRER, START_DORSAL + i));
        // };
        // System.out.println("La carrera va a comenzar");
        // for(int i = 0; i< numCor; i++){
        //     corredores[i].start();
        // };
        // for(int i = 0; i< numCor; i++){
        //     try {
        //         corredores[i].join();
        //     } catch (Exception e) {
        //         // TODO: handle exception
        //     }
        // };
        // System.out.println("La carrera ha terminado");
    }

}
