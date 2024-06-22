package ut02.u03.thread03;

public class Principal {
    public static void main(String[] args) {
        // Crear instancias de Cancion con distintos animales y acciones
        Cancion c1 = new Cancion(6, "Gato", "maullaba");
        Cancion c2 = new Cancion(8, "Perro", "ladraba");
        Cancion c3 = new Cancion(5, "Pato", "nadaba");

        // Crear threads con las canciones
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);

        // Establecer prioridades a los threads
        t1.setPriority(Thread.MAX_PRIORITY);   // Prioridad máxima
        t2.setPriority(Thread.NORM_PRIORITY);  // Prioridad normal (por defecto)
        t3.setPriority(Thread.MIN_PRIORITY);   // Prioridad mínima

        // Iniciar los threads
        t1.start();
        t2.start();
        t3.start();

        // try {
        //     t1.join();
        //     t2.join();
        //     t3.join();
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }
}
