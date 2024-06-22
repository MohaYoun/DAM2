package ut01.u03.explClase;

public class principal {
    public static void main(String[] args) {
        threadClase tc1 = new threadClase("Moha", 4);
        threadClase tc2 = new threadClase("Said", 8);

        Thread t1 = new Thread(tc1);
        Thread t2 = new Thread(tc2);

        // ERROR
        // tc1.run();
        // tc2.run();

        t1.start();
        t2.start();
    }
}
