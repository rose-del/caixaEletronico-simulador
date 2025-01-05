public class ContaCorrente {

    private String nomeDoTitular;
    private int numeroDaConta;
    private double saldo;

    public ContaCorrente(String nomeDoTitular, int numeroDaConta, double saldo) {
        this.nomeDoTitular = nomeDoTitular;
        this.numeroDaConta = numeroDaConta;
        this.saldo = saldo;
    }

    public String getNomeDoTitular() {
        return nomeDoTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double deposito) {
        saldo += deposito;
    }

    public void sacar(double saque) {
        saldo -= saque;
    }

    @Override
    public String toString() {
        return String.format("\nTitular: %s \nConta: %d \nSaldo: %.2f", nomeDoTitular, numeroDaConta, saldo);
    }
}
