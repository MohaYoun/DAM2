package miIntento;
public class Banco implements Runnable{
    //static final int TRANS_MAX = 1001;
    private String nombre;
    private int num_transferencias;
    private int cantidad;
    private int cant_total;
    Object inicio;

    public Banco(String nombre, int num_transferencias, int cantidad, Object inicio) {
        this.nombre = nombre;
        this.num_transferencias = num_transferencias;
        this.cantidad = cantidad;
        this.inicio = inicio;
    }

    @Override
    public void run() {
        synchronized(inicio){
            try {
                
                inicio.wait();
                for (int i = 1; i <= num_transferencias; i++) {
                    cant_total += cantidad;
                    System.out.println(String.format("Soy %s, voy por la transferencia numero %d,"+ 
                    "y llevo la cantidad ingresada de %d", nombre, i, cant_total));
                }  
            } catch (Exception e) {
                // TODO: handle exception
            }
            inicio.notify();             
        }
        
        System.out.println("He finalizado mi transaccion!!!");
        System.out.println("Introduce cualquier tecla para iniciar la siguiente transaccion");
        // synchronized(inicio){
        //     inicio.notify();   
        // }
    }
}
