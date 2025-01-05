# SIMULADOR DE CAIXA ELETRÔNICO

Este projeto consiste em um sistema de servidor bancário simples desenvolvido em Java. 
Ele permite aos usuários interagir com contas correntes através de um cliente conectado ao servidor, 
possibilitando operações como consulta de saldo, depósitos e saques.

## Funcionalidades

- **Consulta de Saldo:** O cliente pode verificar o saldo de uma conta informando o número da conta.

- **Depósito:** O cliente pode realizar depósitos informando o número da conta e o valor.

- **Saque:** O cliente pode realizar saques informando o número da conta e o valor.

## Estrutura do Projeto

O projeto é composto por dois arquivos principais:

1. **ServidorBancario.java**

    - Gerencia as contas correntes em um `HashMap`.

    - Escuta conexões de clientes na porta 12345.

    - Trata comandos enviados pelo cliente (consulta de saldo, depósito, saque).

2. **ClienteBancario.java**

    - Apresenta uma interface gráfica simples para interagir com o servidor.

    - Permite ao usuário realizar as operações bancárias mencionadas.

3. **ContaCorrente.java**

    - Representa uma conta corrente com atributos para o nome do titular, número da conta e saldo.

    - Possui métodos para consultar saldo, depositar e sacar valores.

    - O código dessa parte foi desenvolvido em um projeto antigo e serviu como base para a ideia de
      criar a interface atual do sistema. Inclui uma implementação de toString para exibição amigável
      dos detalhes da conta.

    - Aos interessados, o código de inspiração está disponível em: https://github.com/rose-del/ex006-Java-POO


## Como Excutar

### Por linha de comando:

**Servidor**
1. Certifique-se de ter o Java instalado em sua máquina.

2. Compile o arquivo ServidorBancario.java:
```bash
javac ServidorBancario.java
```

3. Execute o servidor:
```bash
java ServidorBancario
//O servidor iniciará na porta 12345.
```

**Cliente**
1. Compile o arquivo ClienteBancario.java:
```bash
javac ClienteBancario.java
```

2. Execute o cliente:
```bash
java ClienteBancario
```

3. Utilize a interface para interagir com o servidor.

### Pela IDE
[Caixa 24h](https://github.com/user-attachments/assets/d25108d0-8c05-4fad-ae1d-4b0d75ae0d47)
