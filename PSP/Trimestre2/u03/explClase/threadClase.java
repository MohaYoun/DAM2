package ut01.u03.explClase;

public class threadClase implements Runnable {
    private String nombre;
    private int veces;

    public threadClase(String nombre, int veces) {
        this.nombre = nombre;
        this.veces = veces;
    }

    @Override
    public void run() {
        for (int i = 0; i < veces; i++) {
            // TODO Auto-generated method stub
            System.out.println(nombre);
        }
    }

}
