package ut02.u03.thread01;

public class principal {
    public static void main(String[] args) {
        // Numeros n1 = new Numeros();
        // n1.start();
        // Saludo n1 = new Saludo();
        // Thread t1 = new Thread(n1);
        // t1.run();
        Runnable runnable = 
        () -> { System.out.println("Hola Mundo"); };
        runnable.run();
    }
}
