
public class Incrementador implements Runnable {

    Contador cont;

    public Incrementador(Contador cont) {
        this.cont = cont;
    }

    @Override
    public void run() {
        for (int i = 0; i < PrincipalTh4.NVECES; i++) {
            cont.increment();
        }
    }

}
