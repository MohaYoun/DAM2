package ut02.u03.thread03;

public class Cancion implements Runnable {
    static final int MAX_ITERACIONES = 20;
    static final int TIEMPO = 1000;
    private static final int NUM_MIN_ALEAT = 10000;
    private static final int NUM_MAX_ALEAT = 30000;

    private int numero;
    private String animal;
    private String accion;

    public Cancion(int numero, String animal, String accion) {
        this.numero = numero;
        this.animal = animal;
        this.accion = accion;
    }

    // Método para verificar si un número es primo
    private boolean esPrimo(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        for (int i = 1; i <= numero; i++) {
            System.out.println(i + " " + animal + " " + accion + " sobre la tela de una araña");

            // Generar número aleatorio entre 100000 y 300000
            int numeroAleatorio = (int) (NUM_MIN_ALEAT + Math.random()*NUM_MAX_ALEAT);

            // Verificar si el número aleatorio es primo
            esPrimo(numeroAleatorio);

            System.out.println("Como veían que no se caian, fueron a llamar a otro " + animal);
            try {
                Thread.sleep(TIEMPO);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Llamaron a otro " + animal + " y...");
        System.out.println("SE CAYERONN!!!");
    }
}
