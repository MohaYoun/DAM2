package ut02.u03.thread02;

public class Numeros implements Runnable{
    int num;

    public Numeros() {
    }
    public Numeros(int num) {
        this.num = num;
    }

    // @Override
    // public void run(){
    //     for (int i = 0; i < 11; i++) {
    //         System.out.println(i);
    //     }
    // }
    
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + num + " * " + i + " = " + (num * i));
        }
    }
}
