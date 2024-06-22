package EjercicioRepasoJunio.Java.SINC_FabricaCoche;

public class Inspector implements Runnable{
    
    private static final int MIN_EJECUCION_CONCURRENTE = 500;
    private static final double EJECUCION_CONCURRENTE = 750;
    private int id;
    private Fabrica fabrica;

    public Inspector(int id, Fabrica fabrica) {
        this.id = id;
        this.fabrica = fabrica;
    }

    @Override
    public void run() {
        while (true) {
            int cocheInspeccionado = fabrica.inspeccionar();
            
            // Es concurrente
            try {
                Thread.sleep(MIN_EJECUCION_CONCURRENTE + (int) (Math.random() * EJECUCION_CONCURRENTE));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Inspector " + id + " ha inspeccionado el coche " + cocheInspeccionado);
        }
        // for (int i = 0; i < VECES_INSPECCIONA; i++) {
        //     int cocheInspeccionado = fabrica.inspeccionar();
            
        //     // Es concurrente
        //     try {
        //         Thread.sleep(MIN_EJECUCION_CONCURRENTE + (int) (Math.random() * EJECUCION_CONCURRENTE));
        //     } catch (InterruptedException e) {
        //         e.printStackTrace();
        //     }
        //     System.out.println("Inspector " + id + " ha inspeccionado el coche " + cocheInspeccionado);
        // }
    }
}
