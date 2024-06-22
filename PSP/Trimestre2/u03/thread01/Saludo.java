package ut02.u03.thread01;

/*public class Numeros extends Thread{
    
    @Override
    public void run(){
        System.out.println("Hola Mundo");
    }

}*/
public class Saludo implements Runnable{

    public void run(){
        System.out.println("Hola Mundo");
    }

    
}
