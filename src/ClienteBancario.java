import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ClienteBancario {
    private JFrame frame;
    private JLabel lblResposta;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClienteBancario() {
        try {
            socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        frame = new JFrame("CAIXA ELETRÔNICO");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(181, 207, 247));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("CAIXA 24H");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(lblTitulo, gbc);

        JLabel lblOperacao = new JLabel("Insira o seu cartão:");
        lblOperacao.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        frame.add(lblOperacao, gbc);

        JTextField txtOperacao = new JTextField(15);
        txtOperacao.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        frame.add(txtOperacao, gbc);

        JLabel lblOpcao = new JLabel("O que deseja realizar:");
        lblOpcao.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        frame.add(lblOpcao, gbc);

        JTextField txtOpcao = new JTextField(15);
        txtOpcao.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        frame.add(txtOpcao, gbc);

        JLabel lblValor = new JLabel("Valor:");
        lblValor.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        frame.add(lblValor, gbc);

        JTextField txtValor = new JTextField(15);
        txtValor.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        frame.add(txtValor, gbc);

        JButton btnEnviar = new JButton("Confirmar");
        lblResposta = new JLabel("");
        lblResposta.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(btnEnviar, gbc);

        gbc.gridy = 7;
        frame.add(lblResposta, gbc);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcao = txtOpcao.getText();
                String conta = txtOperacao.getText();
                String valor = txtValor.getText();

                String comando;
                if (opcao.equals("1")) {
                    comando = opcao + " " + conta;
                } else if (opcao.equals("2") || opcao.equals("3")) {
                    comando = opcao + " " + conta + " " + valor;
                } else {
                    lblResposta.setText("Opção inválida!");
                    return;
                }

                try {
                    out.println(comando); // Envia o comando para o servidor
                    lblResposta.setText(in.readLine()); // Exibe a resposta do servidor
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ClienteBancario cliente = new ClienteBancario();
        cliente.mostrar();
    }
}
