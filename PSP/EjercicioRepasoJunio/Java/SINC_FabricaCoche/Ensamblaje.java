package EjercicioRepasoJunio.Java.SINC_FabricaCoche;

public class Ensamblaje implements Runnable{

    private static final double TIEMPO_EN_PRODUCIR = 2000;
    private static final double MAX_TIEMPO_PRODUCIR = 3000;
    private static final double MAX_NUMERO_PRODUCIR = 2;
    private static final int MIN_NUMERO_PRODUCIR = 1;
    private int id;
    private Fabrica fabrica;
    
    public Ensamblaje(int id, Fabrica fabrica) {
        this.id = id;
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        while (true) {
            // Es concurrente
            System.out.println("Ensamblador " + id + " comienza producción");
            try {
                Thread.sleep((int) (TIEMPO_EN_PRODUCIR + (Math.random() * MAX_TIEMPO_PRODUCIR)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int coche = (int) (MIN_NUMERO_PRODUCIR + Math.random() * MAX_NUMERO_PRODUCIR);
            System.out.println("Ensamblador " + id + " ha finalizado la producción. Ha producido la parte " + coche);
            // Es concurrente

            fabrica.almacenar(coche);
            System.out.println("Ensamblador " + id + " ha almacenado la parte " + coche);
        }
    }
}
