
public class PrincipalTh4 {
    public static final int NVECES = 1000;

    public static void main(String[] args) {

        Contador c = new Contador();

        Thread t1 = new Thread(
                new Incrementador(c));

        Thread t2 = new Thread(
                new Decrementador(c));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("El contenido es: %s", c));
    }
}
