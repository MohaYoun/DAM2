class CuentaBancaria {
    private String titular;
    private double saldo;

    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    // Método synchronized para realizar una transferencia entre cuentas
    public synchronized void transferir(CuentaBancaria destino, double cantidad) {
    //public void transferir(CuentaBancaria destino, double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            destino.depositar(cantidad);
            System.out.println(titular + " transfirió " + cantidad + " euros a " + destino.getTitular());
        } else {
            System.out.println(titular + " no tiene suficientes fondos para transferir " + cantidad + " euros.");
        }
    }

    // Método synchronized para depositar dinero en la cuenta
    public synchronized void depositar(double cantidad) {
    //public void depositar(double cantidad) {
        saldo += cantidad;
    }
}