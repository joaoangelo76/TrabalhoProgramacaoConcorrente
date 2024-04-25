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

class Banco {
    public static void main(String[] args) {
        Conta contaBanco = new Conta(0);
        Conta contaLoja1 = new Conta(0);
        Conta contaLoja2 = new Conta(0);
        Conta contaInvestimentoFuncionario1 = new Conta(0);
        Conta contaInvestimentoFuncionario2 = new Conta(0);
        Conta contaInvestimentoFuncionario3 = new Conta(0);
        Conta contaInvestimentoFuncionario4 = new Conta(0);

        Loja loja1 = new Loja(contaLoja1);
        Loja loja2 = new Loja(contaLoja2);

        Funcionario funcionario1 = new Funcionario(contaLoja1, contaInvestimentoFuncionario1);
        Funcionario funcionario2 = new Funcionario(contaLoja1, contaInvestimentoFuncionario2);
        Funcionario funcionario3 = new Funcionario(contaLoja2, contaInvestimentoFuncionario3);
        Funcionario funcionario4 = new Funcionario(contaLoja2, contaInvestimentoFuncionario4);

        Cliente[] clientes = new Cliente[5];
        for (int i = 0; i < 5; i++) {
            clientes[i] = new Cliente(contaBanco);
        }

        // Iniciar as threads
        loja1.pagarSalarios();
        loja2.pagarSalarios();
        funcionario1.start();
        funcionario2.start();
        funcionario3.start();
        funcionario4.start();
        for (Cliente cliente : clientes) {
            cliente.start();
        }

        // Esperar pelas threads dos clientes terminarem
        try {
            for (Cliente cliente : clientes) {
                cliente.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exibir os saldos finais das contas
        System.out.println("Saldo final da conta do banco: R$ " + contaBanco.getSaldo());
        System.out.println("Saldo final da conta da loja 1: R$ " + contaLoja1.getSaldo());
        System.out.println("Saldo final da conta da loja 2: R$ " + contaLoja2.getSaldo());
        System.out.println("Saldo final da conta de investimento do funcionário 1: R$ " + contaInvestimentoFuncionario1.getSaldo());
        System.out.println("Saldo final da conta de investimento do funcionário 2: R$ " + contaInvestimentoFuncionario2.getSaldo());
        System.out.println("Saldo final da conta de investimento do funcionário 3: R$ " + contaInvestimentoFuncionario3.getSaldo());
        System.out.println("Saldo final da conta de investimento do funcionário 4: R$ " + contaInvestimentoFuncionario4.getSaldo());
    }
}