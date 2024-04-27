# TrabalhoProgramacaoConcorrente
(Este repositório é referente ao trabalho 1 de Programação Concorrente)

Segue abaixo a descrição do projeto:

Este projeto tem como objetivo criar um sistema bancário utilizando threads.

O sistema deve conter pelo menos as seguintes entidades:
● Banco;
● Loja;
● Funcionário;
● Cliente;
● Conta;

E deve conter um total de:
● 1 banco;
● 2 lojas;
● 4 funcionários (2 por loja);
● 5 clientes;

Cada cliente:
● Deve ser uma thread;
● Deve possuir uma conta com um saldo inicial de R$ 1.000,00;
● Deve realizar compras, de R$ 100,00 ou R$ 200,00, alternando as lojas, até o saldo da conta estar
vazio.

Cada loja:
● Deve possuir uma conta, para receber os pagamentos dos clientes;
● Deve pagar os funcionários assim que possuir o valor dos seus salários (R$ 1.400,00);

Cada funcionário:
● Deve ser uma thread;
● Deve possuir duas contas, uma para receber o salário da loja e outra de investimentos;
● Deve investir 20% do salário na conta de investimentos logo após seu recebimento.
O banco:
● Deve intermediar as transações de forma síncrona e coordenada, garantindo a consistência dos
saldos das contas.

Além disso, o sistema deve exibir o valor das transferências e o saldo final de cada conta, garantindo que os saldos estejam consistentes ao fim da execução, independente da ordem em que as transações foram feitas.

