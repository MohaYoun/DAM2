
public class Contador {

    int c;

    public Contador() {
        c = 0;
    }

    public synchronized void increment(){
    c = c + 1;
    }

    public synchronized void decrement(){
    c = c - 1;
    }

    // public void increment() {
    //     int d;
    //     for (int i = 0; i < PrincipalTh4.NVECES; i++) {
    //         d = i * i * i;
    //     }

    //     synchronized (this) {
    //         c = c + 1;
    //     }
    // }

    // public void decrement() {
    //     int d;
    //     for (int i = 0; i < PrincipalTh4.NVECES; i++) {
    //         d = i * i * i;
    //     }

    //     synchronized (this) {
    //         c = c - 1;
    //     }
    // }

    @Override
    public String toString() {
        return String.valueOf(c);
    }

}
