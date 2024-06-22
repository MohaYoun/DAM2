package miIntento;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Principalth5 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Object inicio = new Object();
        Banco b1 = new Banco("Ana", 1000, 10, inicio);
        Banco b2 = new Banco("Capo", 1000, 10, inicio);

        Thread th1 = new Thread(b1);
        Thread th2 = new Thread(b2);

        th1.start();

        System.out.println("Introduce cualquier tecla para iniciar la 1 transaccion");
        try {
            br.readLine();
        } catch (Exception e) {
            // TODO: handle exception
        }
        synchronized(inicio){
            inicio.notify();
        }
        th2.start();
        try {
            br.readLine();
        } catch (Exception e) {
            // TODO: handle exception
        }

        synchronized(inicio){
            inicio.notify();
        }

        try {
            th1.join();
            th2.join();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
