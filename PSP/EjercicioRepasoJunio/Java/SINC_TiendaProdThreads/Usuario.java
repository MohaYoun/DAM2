package EjercicioRepasoJunio.Java.SINC_TiendaProdThreads;

import java.util.ArrayList;
import java.util.Collections;

public class Usuario implements Runnable{
    private static final int ACCION_ANIADE = 2;
    private static final int ACCION_ELIMINA = 1;
    private static final double EJECUCION_CONCURRENTE = 1500;
    private static final int MIN_EJECUCION_CONCURRENTE = 500;

    private CarritoCompras carrito;
    private int cantAniadir;
    private int cantEliminar;
    private ArrayList<Integer> acciones;

    public Usuario(CarritoCompras carrito, int cantAniadir, int cantEliminar) {
        this.carrito = carrito;
        this.cantAniadir = cantAniadir;
        this.cantEliminar = cantEliminar;
        this.acciones = generadorAcciones();
    }
    // Ten en cuenta que en esta tienda especial varios usuarios comparten el carrito. El carrito tendrá capacidad para 10 elementos. 
    // Los usuario añaden 4 elementos y eliminan 2, estas operaciones están entremezcladas de forma aleatoria. De esta forma el 
    // número total de productos que cada usuario aporta es de 2. Cuando un usuario quiere añadir un elemento y el carrito está lleno, 
    // el usuario se bloquea hasta que se libere un hueco. Entre acción cada usuario espera un tiempo aleatorio entre 500 y 1500 milisegundos.

    public ArrayList<Integer> generadorAcciones(){
        acciones = new ArrayList<Integer>();
        for (int i = 0; i < cantAniadir; i++) {
            acciones.add(ACCION_ANIADE);            
        }
        for (int i = 0; i < cantEliminar; i++) {
            acciones.add(ACCION_ELIMINA);            
        }
        Collections.shuffle(acciones);

        return acciones;
    }

    @Override
    public void run() {
        //int sumaAcciones = cantAniadir+cantEliminar;
        for (Integer accion : acciones) {
            try {
                if (accion == ACCION_ANIADE) {
                    carrito.aniadirCarrito(ACCION_ANIADE);
                }else if( accion == ACCION_ELIMINA){
                    carrito.eliminarCarrito(ACCION_ELIMINA);
                }
                
                Thread.sleep((int) (MIN_EJECUCION_CONCURRENTE+(Math.random()*EJECUCION_CONCURRENTE-MIN_EJECUCION_CONCURRENTE)));
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            
        }
            
    }
}
