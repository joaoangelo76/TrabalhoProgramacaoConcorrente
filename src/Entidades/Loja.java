package Entidades;

public class Loja {
    private Conta conta;

    public Loja(Conta conta) {
        this.conta = conta;
    }

    public synchronized void pagarSalarios() {
        double totalSalarios = 0;
        // Calcular o total dos salários dos funcionários
        // Pagar os salários
        System.out.println("Loja pagou R$ " + totalSalarios + " em salários");
    }
}