import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector; // Para uso com JComboBox

public class TelaListaTarefas extends JFrame {

    private JTextField txtId;
    private JTextField txtDataTarefa; // Simplificado para JTextField
    private JTextArea txtDescricaoTarefa;
    private JTextArea txtObservacao;
    private JComboBox<String> cmbPrioridade;
    private JComboBox<String> cmbResponsavel;
    private JTable tabelaTarefas;

    public TelaListaTarefas() {
        super("Gerenciamento de Tarefas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Painel Superior (Formulário e Ações) ---
        JPanel pnlSuperior = new JPanel(new BorderLayout(10, 10));

        // Formulário (GridBagLayout para organização mais complexa)
        JPanel pnlFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento interno
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Linha 1: ID e Data
        gbc.gridx = 0; gbc.gridy = 0;
        pnlFormulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; txtId = new JTextField(5); txtId.setEditable(false);
        pnlFormulario.add(txtId, gbc);

        gbc.gridx = 2;
        pnlFormulario.add(new JLabel("Data da Tarefa (YYYY-MM-DD):"), gbc);
        gbc.gridx = 3; txtDataTarefa = new JTextField(10);
        pnlFormulario.add(txtDataTarefa, gbc);


        // Linha 2: Descrição da Tarefa
        gbc.gridx = 0; gbc.gridy = 1;
        pnlFormulario.add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; // Ocupa 3 colunas
        txtDescricaoTarefa = new JTextArea(3, 20);
        JScrollPane scrollDescricao = new JScrollPane(txtDescricaoTarefa);
        pnlFormulario.add(scrollDescricao, gbc);
        gbc.gridwidth = 1; // Reseta para 1 coluna

        // Linha 3: Observação
        gbc.gridx = 0; gbc.gridy = 2;
        pnlFormulario.add(new JLabel("Observação:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtObservacao = new JTextArea(3, 20);
        JScrollPane scrollObservacao = new JScrollPane(txtObservacao);
        pnlFormulario.add(scrollObservacao, gbc);
        gbc.gridwidth = 1;

        // Linha 4: Prioridade e Responsável
        gbc.gridx = 0; gbc.gridy = 3;
        pnlFormulario.add(new JLabel("Prioridade:"), gbc);
        gbc.gridx = 1; cmbPrioridade = new JComboBox<>(new Vector<String>()); // Mock de dados
        pnlFormulario.add(cmbPrioridade, gbc);

        gbc.gridx = 2;
        pnlFormulario.add(new JLabel("Responsável:"), gbc);
        gbc.gridx = 3; cmbResponsavel = new JComboBox<>(new Vector<String>()); // Mock de dados
        pnlFormulario.add(cmbResponsavel, gbc);

        // Botões de Ação
        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnLimpar = new JButton("Limpar Campos");
        pnlBotoes.add(btnSalvar);
        pnlBotoes.add(btnExcluir);
        pnlBotoes.add(btnLimpar);

        pnlSuperior.add(pnlFormulario, BorderLayout.NORTH);
        pnlSuperior.add(pnlBotoes, BorderLayout.CENTER);
        add(pnlSuperior, BorderLayout.NORTH);

        // --- Pesquisa e Listagem (Centro) ---
        JPanel pnlListagem = new JPanel(new BorderLayout(5, 5));

        // Painel de Pesquisa (simplificado)
        JPanel pnlPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtPesquisa = new JTextField(20);
        JButton btnPesquisar = new JButton("Pesquisar");
        pnlPesquisa.add(new JLabel("Buscar Descrição/Responsável:"));
        pnlPesquisa.add(txtPesquisa);
        pnlPesquisa.add(btnPesquisar);
        pnlListagem.add(pnlPesquisa, BorderLayout.NORTH);

        // Tabela de Resultados
        String[] colunas = {"ID", "Descrição", "Data", "Prioridade", "Responsável"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaTarefas = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaTarefas);
        pnlListagem.add(scrollPane, BorderLayout.CENTER);

        add(pnlListagem, BorderLayout.CENTER);

        // Adiciona dados de Mock para os ComboBoxes (Em produção, viriam do banco)
        cmbPrioridade.addItem("Alta");
        cmbPrioridade.addItem("Média");
        cmbPrioridade.addItem("Baixa");

        cmbResponsavel.addItem("João");
        cmbResponsavel.addItem("Maria");
        cmbResponsavel.addItem("Pedro");
    }
}