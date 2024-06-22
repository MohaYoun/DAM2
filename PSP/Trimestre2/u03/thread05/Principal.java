public class Principal {
    public static void main(String[] args) {
        // Crear dos cuentas de usuarios
        CuentaBancaria cuentaAlice = new CuentaBancaria("Alice", 20000);
        CuentaBancaria cuentaBob = new CuentaBancaria("Bob", 30000);
        CuentaBancaria cuentaDestino = new CuentaBancaria("Said", 0);

        Usuario bob = new Usuario("Bob", cuentaBob, cuentaDestino);
        Usuario alice = new Usuario("Alice", cuentaAlice, cuentaDestino);

        // Crear dos threads, uno para cada usuario, que realizarán las transferencias
        Thread threadBob = new Thread(bob);
        Thread threadAlice = new Thread(alice);

        // Thread threadAlice = new Thread(new Usuario("Alice", cuentaAlice, cuentaBob));
        // Thread threadBob = new Thread(new Usuario("Bob", cuentaBob, cuentaAlice));

        // Iniciar los threads
        threadAlice.start();
        threadBob.start();

        try {
            // Esperar a que ambos threads terminen
            threadAlice.join();
            threadBob.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Mostrar el saldo final en ambas cuentas
        System.out.println("Saldo final en la cuenta de Alice: " + cuentaAlice.getSaldo());
        System.out.println("Saldo final en la cuenta de Bob: " + cuentaBob.getSaldo());
        System.out.println("Saldo final en la cuenta de Said: " + cuentaDestino.getSaldo());
    }
}
