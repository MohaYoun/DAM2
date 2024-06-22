package ut02.u03.thread06;

public class Corredorth6 implements Runnable{
    public static final long TIEMPO_DESCANSO = 500;
    public static final double MAX_INTERVALO_KM = 10;
    int kmRecorrido;
    int kmTotales;
    int dorsal;
    Object salida;

    public Corredorth6(int kmTotales, int dorsal, Object salida) {
        this.kmTotales = kmTotales;
        this.dorsal = dorsal;
        this.kmRecorrido = 0;
        this.salida = salida;
    }

    @Override
    public void run() {
        synchronized(salida){
            try {
                salida.wait();
                System.out.println(String.format("Soy el dorsal %d inicio mi carrera.", dorsal));
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        while(kmRecorrido < kmTotales){
            try {
                Thread.sleep((long)(Math.random()*TIEMPO_DESCANSO)+TIEMPO_DESCANSO);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            kmRecorrido+=Math.random()*MAX_INTERVALO_KM;
            System.out.println(String.format("Dorsal %d, ha recorrido %d/100",dorsal, kmRecorrido));
        }
        synchronized(salida){
            salida.notify();
        }
        System.out.println(String.format("Soy el dorsal %d TERMINE!!", dorsal));
    }
}
