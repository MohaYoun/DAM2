package EjercicioRepasoJunio.Java.SINC_FloristeriaUDP;

import java.util.ArrayList;

public class Floristeria {
    ArrayList<Integer> almacen = new ArrayList<Integer>();
    int capacidad;

    public Floristeria(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void almacenar(int producto) {
        while (almacen.size() == capacidad) {
            try {
                System.out.println("Lo siento el almacén está lleno");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        almacen.add(producto);
        System.out.println("Almacen - Producto almacenado: " + producto);
        notifyAll();
    }

    public synchronized int extraer() {
        while (almacen.size() == 0) { 
            try {
                System.out.println("Lo siento el almacén está vacío");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int producto = almacen.remove(0);
        //System.out.println("Almacen - Producto extraído: " + producto);
        notifyAll();
        return producto;
    }

}
