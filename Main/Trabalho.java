import java.util.concurrent.ThreadLocalRandom;

package Main;

import Entidades.*;

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