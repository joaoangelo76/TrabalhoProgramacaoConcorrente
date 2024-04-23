import java.util.concurrent.ThreadLocalRandom;

class Conta {
    private double saldo;

    public Conta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void depositar(double valor) {
        saldo += valor;
    }

    public synchronized void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
        }
    }

    public synchronized double getSaldo() {
        return saldo;
    }
}

class Cliente extends Thread {
    private static final int[] VALORES_COMPRA = {100, 200};
    private Conta conta;

    public Cliente(Conta conta) {
        this.conta = conta;
    }

    @Override
    public void run() {
        while (true) {
            int valorCompra = VALORES_COMPRA[ThreadLocalRandom.current().nextInt(VALORES_COMPRA.length)];
            synchronized (conta) {
                if (conta.getSaldo() >= valorCompra) {
                    conta.sacar(valorCompra);
                    System.out.println(Thread.currentThread().getName() + " realizou uma compra de R$ " + valorCompra);
                } else {
                    break;
                }
            }
        }
    }
}

class Funcionario extends Thread {
    private static final double SALARIO = 1400;
    private Conta contaSalario;
    private Conta contaInvestimento;

    public Funcionario(Conta contaSalario, Conta contaInvestimento) {
        this.contaSalario = contaSalario;
        this.contaInvestimento = contaInvestimento;
    }

    @Override
    public void run() {
        synchronized (contaSalario) {
            contaSalario.depositar(SALARIO);
            double valorInvestimento = SALARIO * 0.2;
            contaInvestimento.depositar(valorInvestimento);
            System.out.println(Thread.currentThread().getName() + " recebeu o salário de R$ " + SALARIO +
                    " e investiu R$ " + valorInvestimento);
        }
    }
}

class Loja {
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