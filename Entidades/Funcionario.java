package Entidades;

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