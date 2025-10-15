import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormCadastroTipoCapa extends JFrame {

    // Componentes da interface
    private JTextField txtId;
    private JTextField txtNome;
    private JButton btnSalvar;
    private JButton btnLimpar;
    private JButton btnSair;

    /**
     * Construtor da classe FormCadastroTipoCapa.
     * Configura a janela e inicializa os componentes.
     */
    public FormCadastroTipoCapa() {
        // Configurações da janela
        setTitle("Cadastro de Tipo de Capa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200); // Tamanho ajustado para apenas dois campos
        setLocationRelativeTo(null); // Centralizar na tela

        // Inicializar componentes
        inicializarComponentes();

        // Tornar a janela visível
        setVisible(true);
    }

    /**
     * Inicializa e posiciona os componentes na interface usando GridBagLayout.
     */
    private void inicializarComponentes() {
        // Criar painel principal
        JPanel painelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurações gerais do GridBagConstraints
        gbc.insets = new Insets(5, 5, 5, 5); // Margem entre componentes
        gbc.anchor = GridBagConstraints.WEST; // Alinhar à esquerda

        // --- Campos de Cadastro ---

        // Campo ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        painelPrincipal.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtId = new JTextField(15);
        painelPrincipal.add(txtId, gbc);

        // Campo Nome (Descrição)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        painelPrincipal.add(new JLabel("Descrição:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtNome = new JTextField(15);
        painelPrincipal.add(txtNome, gbc);

        // --- Painel de Botões ---

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centralizado
        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");

        // Adicionar Listeners aos botões
        btnSalvar.addActionListener(e -> salvarTipoCapa());
        btnLimpar.addActionListener(e -> limparCampos());
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha apenas esta janela
            }
        });

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSair);

        // Adicionar painel de botões ao painel principal
        gbc.gridx = 0;
        gbc.gridy = 2; // Posição abaixo dos campos
        gbc.gridwidth = 2; // Ocupa duas colunas
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelPrincipal.add(painelBotoes, gbc);

        // Adicionar painel principal à janela
        add(painelPrincipal);
    }

    /**
     * Valida os campos, cria o objeto TipoCapa e salva no arquivo.
     */
    private void salvarTipoCapa() {
        String idStr = txtId.getText().trim();
        String nome = txtNome.getText().trim();

        // 1. Validação de campos obrigatórios
        if (idStr.isEmpty() || nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos: ID e Descrição.",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Validação de campo numérico (ID)
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "O campo ID deve conter apenas números inteiros!",
                    "Valor inválido",
                    JOptionPane.ERROR_MESSAGE);
            txtId.requestFocus();
            return;
        }

        try {
            // Cria o objeto TipoCapa
            TipoCapa tipoCapa = new TipoCapa();
            tipoCapa.setId(id);
            tipoCapa.setNome(nome);

            // Chama o método salvar da classe TipoCapa e salva no arquivo
            tipoCapa.salvar(); 
            salvarNoArquivo(tipoCapa);

            JOptionPane.showMessageDialog(this,
                    "Tipo de Capa cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparCampos();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar dados no arquivo 'tiposcapa.txt': " + e.getMessage(),
                    "Erro de I/O",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this,
                    "Erro inesperado ao salvar: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Escreve os dados do Tipo de Capa em um arquivo de texto.
     * @param tipoCapa O objeto TipoCapa a ser salvo.
     * @throws IOException Se ocorrer um erro de I/O ao escrever no arquivo.
     */
    private void salvarNoArquivo(TipoCapa tipoCapa) throws IOException {
        // Obter data e hora atual
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHora = agora.format(formatter);

        // Criar o conteúdo a ser salvo
        String conteudo = String.format("""
                === TIPO DE CAPA CADASTRADO ===
                Data/Hora: %s
                ID: %d
                Descrição: %s
                ==============================

                """,
                dataHora,
                tipoCapa.getId(),
                tipoCapa.getNome()); 

        try (FileWriter writer = new FileWriter("tiposcapa.txt", true)) {
            writer.write(conteudo);
        }
    }

    /**
     * Limpa todos os campos do formulário e foca no campo ID.
     */
    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtId.requestFocus(); // Focar no primeiro campo
    }

    /**
     * Método main para testar o formulário.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormCadastroTipoCapa::new);
    }
}