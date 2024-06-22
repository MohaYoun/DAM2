package EjercicioRepasoJunio.Java.SINC_FabricaCoche;

import java.util.ArrayList;

public class Fabrica {

    ArrayList<Integer> fabrica;

    public Fabrica(ArrayList<Integer> fabrica) {
        this.fabrica = fabrica;
    }

    public synchronized void almacenar(int coche) {
        fabrica.add(coche);
        System.out.println("Fabrica - Parte almacenada: " + coche);
        notifyAll();
    }

    public synchronized int inspeccionar() {
        while (fabrica.size() <= 0) { 
            try {
                System.out.println("Lo siento la fabrica está vacía");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int cocheInspeccionado = fabrica.remove(0);
        System.out.println("Fabrica - Cohe retirado para inspeccionar: " + cocheInspeccionado);
        notifyAll();
        return cocheInspeccionado;
    }
}
