package EjercicioRepasoJunio.Java.SINC_FloristeriaUDP;

public class Empleado implements Runnable {
    private static final int VECES_PRODUCE = 20;
    private static final double MIN_TIEMPO_EN_PREPARAR = 3000;
    private static final double TIEMPO_EN_PREPARAR = 4000;
    private static final double MAX_NUMERO_PRODUCTO = 2;
    private static final double MIN_NUMERO_PRODUCTO = 1;
    private Floristeria almacen;
    private int id;

    public Empleado(Floristeria almacen, int id) {
        this.almacen = almacen;
        this.id = id;
    }

    public void run() {
        for (int i = 0; i < VECES_PRODUCE ; i++) {
            // Es concurrente
            System.out.println("Empleado " + id + " comienza a preparar");
            try {
                Thread.sleep((int) (MIN_TIEMPO_EN_PREPARAR + (Math.random() * TIEMPO_EN_PREPARAR)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //int producto = (int) (Math.random() * MAX_NUMERO_PRODUCTO);
            int producto =((int) (MIN_NUMERO_PRODUCTO + (Math.random() * MAX_NUMERO_PRODUCTO)));
            System.out.println("Empleado " + id + " ha finalizado la preparacion. Ha preparado " + producto + " ramos.");
            // Es concurrente

            for (int j = 0; j < producto; j++) {
                almacen.almacenar(producto);
            }
            
            System.out.println("Empleado " + id + " ha almacenado los ramos " + producto);
        }
    }
}
