package EjercicioRepasoJunio.Java.UDP_SistCondAutn;

public class Principal {
    private static final int MIN_KILOMETRO = 1;

    public static void main(String[] args) {
        // ClienteSensor c1 = new ClienteSensor(1, 65);
        // ClienteSensor c2 = new ClienteSensor(2, 120);
        // ClienteSensor c3 = new ClienteSensor(3, 200);
        // ClienteSensor c4 = new ClienteSensor(4, 300);
        
        Thread []sensores = new Thread[5];

        for (int i = 0; i < sensores.length; i++) {
            sensores[i] = new Thread(new ClienteSensor(i+1, (int)(MIN_KILOMETRO*Math.random()*500)));
        }
        for (int i = 0; i < sensores.length; i++) {
            sensores[i].start();
        }
        for (int i = 0; i < sensores.length; i++) {
            try {
                sensores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Thread th1 = new Thread(new ClienteSensor(1, 65));
    }
    
}
