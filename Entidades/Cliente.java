package Entidades;

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