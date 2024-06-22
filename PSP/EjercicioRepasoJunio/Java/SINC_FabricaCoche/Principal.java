package EjercicioRepasoJunio.Java.SINC_FabricaCoche;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        Fabrica fabrica = new Fabrica(new ArrayList<>());

        Inspector inspector = new Inspector(1, fabrica);

        Ensamblaje e1 = new Ensamblaje(1, fabrica);
        Ensamblaje e2 = new Ensamblaje(2, fabrica);
        Ensamblaje e3 = new Ensamblaje(3, fabrica);

        Thread[] hilos = new Thread[4];
        hilos[0] = new Thread(e1);
        hilos[1] = new Thread(e2);
        hilos[2] = new Thread(e3);
        hilos[3] = new Thread(inspector);

        for (Thread hilo : hilos) {
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
