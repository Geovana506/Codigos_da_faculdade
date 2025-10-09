import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class FormCadastAutor extends JFrame {
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtCidade;
    private JButton btnSalvar;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnPesquisar;
    
    // Nome do arquivo onde os dados serão salvos
    private static final String NOME_ARQUIVO = "autores.txt";

    public FormCadastAutor() {
        setTitle("Cadastro de Autor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centralizar na tela

        inicializarComponentes();

        setVisible(true);
    }

    private void inicializarComponentes() {
        // Criar painel principal
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurações gerais do GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Campo ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtId = new JTextField(15);
        painelPrincipal.add(txtId, gbc);

        // Campo Nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        painelPrincipal.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtNome = new JTextField(15);
        painelPrincipal.add(txtNome, gbc);

        // Campo Cidade
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        painelPrincipal.add(new JLabel("Cidade:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtCidade = new JTextField(15);
        painelPrincipal.add(txtCidade, gbc);


        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());

        btnSalvar = new JButton("Salvar");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnPesquisar = new JButton("Pesquisar");


        // Adicionar listeners aos botões
        btnSalvar.addActionListener(e -> salvarAutor());

        btnAlterar.addActionListener(e -> alterarCampos());

        btnExcluir.addActionListener(e -> System.exit(0));

        btnPesquisar.addActionListener(e -> System.exit(0));

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnPesquisar);


        // Adicionar painel de botões ao painel principal
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelPrincipal.add(painelBotoes, gbc);

        // Adicionar painel principal à janela
        add(painelPrincipal);
    }

    private void salvarAutor() {
        // Validar se os campos estão preenchidos
        if (txtId.getText().trim().isEmpty() ||
                txtNome.getText().trim().isEmpty() ||
                txtCidade.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos!",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 1. Obter os dados do autor
        String id = txtId.getText().trim();
        String nome = txtNome.getText().trim();
        String cidade = txtCidade.getText().trim();
        
        // Formatar a linha de dados para salvar no arquivo
        // Usando um separador | para facilitar a leitura/processamento posterior
        String dadosAutor = String.format("%s|%s|%s%n", id, nome, cidade);
        
        try (FileWriter escritor = new FileWriter(NOME_ARQUIVO, true)) {
            // 3. Escrever a linha de dados no arquivo
            escritor.write(dadosAutor);
            
            // Exibir mensagem de sucesso
            String mensagemSucesso = String.format(
                "Autor salvo com sucesso no arquivo **%s**!\n\nID: %s\nNome: %s\nCidade: %s",
                NOME_ARQUIVO, id, nome, cidade);

            JOptionPane.showMessageDialog(this, mensagemSucesso, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpar os campos após o salvamento
            limparCampos();
            
        } catch (IOException e) {
            // 5. Tratar erro de I/O (ex: permissão negada, problema de disco)
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar os dados no arquivo: " + e.getMessage(),
                    "Erro de Arquivo",
                    JOptionPane.ERROR_MESSAGE);
            System.err.println("Erro: " + e.getMessage()); // Imprime mensagem de erro no console
        }
    }
    
    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtCidade.setText("");
        txtId.requestFocus(); // Focar no primeiro campo
    }

    private void alterarCampos() {
        // Lógica para alterar os campos (simulação)
        JOptionPane.showMessageDialog(this,
                "Funcionalidade de alterar campos ainda não implementada.",
                "Alterar",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Método main para executar a aplicação
    public static void main(String[] args) {
        // Executar na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            new FormCadastAutor();
        });
    }
}