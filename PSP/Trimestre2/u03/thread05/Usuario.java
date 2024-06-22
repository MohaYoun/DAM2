public class Usuario implements Runnable {
    private static int NUM_TRANSFERENCIAS = 1000;
    private String nombre;
    private CuentaBancaria cuentaOrigen;
    private CuentaBancaria cuentaDestino;

    public Usuario(String nombre, CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino) {
        this.nombre = nombre;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TRANSFERENCIAS; i++) {
            // Realizar transferencia de 10 euros desde la cuenta de origen a la cuenta de destino
            cuentaOrigen.transferir(cuentaDestino, 10);
        }
    }
}
