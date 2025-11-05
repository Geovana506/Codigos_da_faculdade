import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaResponsavel extends JFrame {

    private JTextField txtId;
    private JTextField txtNome;
    private JTable tabelaResponsaveis;

    public TelaResponsavel() {
        super("Gerenciamento de Responsáveis");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Painel Superior (Formulário e Ações) ---
        JPanel pnlSuperior = new JPanel(new BorderLayout(10, 10));

        // Formulário (GridLayout 2x2)
        JPanel pnlFormulario = new JPanel(new GridLayout(2, 2, 5, 5));
        txtId = new JTextField(5);
        txtId.setEditable(false);
        txtNome = new JTextField(20);

        pnlFormulario.add(new JLabel("ID:"));
        pnlFormulario.add(txtId);
        pnlFormulario.add(new JLabel("Nome:"));
        pnlFormulario.add(txtNome);

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

        // Painel de Pesquisa
        JPanel pnlPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtPesquisa = new JTextField(15);
        JButton btnPesquisar = new JButton("Pesquisar");
        pnlPesquisa.add(new JLabel("Buscar Nome:"));
        pnlPesquisa.add(txtPesquisa);
        pnlPesquisa.add(btnPesquisar);
        pnlListagem.add(pnlPesquisa, BorderLayout.NORTH);

        // Tabela de Resultados
        String[] colunas = {"ID", "Nome"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaResponsaveis = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaResponsaveis);
        pnlListagem.add(scrollPane, BorderLayout.CENTER);

        add(pnlListagem, BorderLayout.CENTER);
    }
}