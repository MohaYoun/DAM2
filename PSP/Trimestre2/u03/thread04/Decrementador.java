
public class Decrementador implements Runnable {

    Contador cont;

    public Decrementador(Contador cont) {
        this.cont = cont;
    }

    @Override
    public void run() {
        for (int i = 0; i < PrincipalTh4.NVECES; i++) {
            cont.decrement();
        }
    }
}
