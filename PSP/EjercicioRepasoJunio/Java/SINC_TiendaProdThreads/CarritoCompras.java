package EjercicioRepasoJunio.Java.SINC_TiendaProdThreads;

import java.util.ArrayList;

public class CarritoCompras {
    ArrayList<Integer> carrito = new ArrayList<Integer>();
    //int carrito[];
    int capacidad;

    public CarritoCompras(int capacidad) {
        this.capacidad = capacidad;
    }
    

    public int getCapacidad() {
        return capacidad;
    }


    public synchronized void aniadirCarrito(int producto){
        while (carrito.size() == capacidad) {
            try {
                System.out.println("Carro - Lo siento el carro esta lleno.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carrito.add(producto);
        System.out.println("Carro - Producto añadido : " + producto);
        System.out.println(" carrito: " + carrito);
        notifyAll();
    }

    public synchronized void eliminarCarrito(int producto){
        while (carrito.size() == 0) {
            try {
                System.out.println("Carro - Lo siento el carro esta vacio.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carrito.remove(0);
        System.out.println("Carro - Producto extraido: " + producto);
        System.out.println(" carrito: " + carrito);
        notifyAll();
        //return producto;
    }
    
}
