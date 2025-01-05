import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServidorBancario {
    private static HashMap<String, ContaCorrente> contas = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Iniciado...");

            contas.put("12345", new ContaCorrente("Rose", 12345, 100.00));
            contas.put("67891", new ContaCorrente("Mike", 67891, 400.00));

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClienteHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClienteHandler implements Runnable {
        private Socket socket;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String comando;
                while ((comando = in.readLine()) != null) {
                    String[] partes = comando.split(" ");
                    if (partes.length < 2) {
                        out.println("Erro: Comando inválido!");
                        continue;
                    }

                    String opcao = partes[0];
                    String conta = partes[1];
                    String resposta = "Operação inválida";

                    if (opcao.equals("1")) { // Saldo
                        ContaCorrente c = contas.get(conta);
                        if (c != null) {
                            resposta = "Saldo da conta: R$ " + c.getSaldo();
                        } else {
                            resposta = "Erro: Conta " + conta + " não encontrada.";
                        }
                    } else if (opcao.equals("2")) { // Depósito
                        if (partes.length < 3) {
                            resposta = "Erro: Valor do depósito não informado!";
                        } else {
                            try {
                                double valor = Double.parseDouble(partes[2]);
                                ContaCorrente c = contas.get(conta);
                                if (c != null) {
                                    c.depositar(valor);
                                    resposta = "Depósito realizado com sucesso na conta \n Novo saldo: R$ " + c.getSaldo();
                                } else {
                                    resposta = "Erro: Conta " + conta + " não encontrada.";
                                }
                            } catch (NumberFormatException e) {
                                resposta = "Erro: Valor do depósito inválido!";
                            }
                        }
                    } else if (opcao.equals("3")) { // Saque
                        if (partes.length < 3) {
                            resposta = "Erro: Valor do saque não informado!";
                        } else {
                            try {
                                double valor = Double.parseDouble(partes[2]);
                                ContaCorrente c = contas.get(conta);
                                if (c != null) {
                                    try {
                                        c.sacar(valor);
                                        resposta = "Saque realizado com sucesso na conta \n Novo saldo: R$ " + c.getSaldo();
                                    } catch (IllegalArgumentException e) {
                                        resposta = e.getMessage();
                                    }
                                } else {
                                    resposta = "Erro: Conta " + conta + " não encontrada.";
                                }
                            } catch (NumberFormatException e) {
                                resposta = "Erro: Valor do saque inválido!";
                            }
                        }
                    }

                    out.println(resposta);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
