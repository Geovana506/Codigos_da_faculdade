import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaPrioridade extends JFrame {

    private JTextField txtId;
    private JTextField txtDescricao;
    private JTable tabelaPrioridades;

    public TelaPrioridade() {
        super("Gerenciamento de Prioridades");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Layout Principal: BorderLayout para separar Formulário/Botões e Tabela
        setLayout(new BorderLayout(10, 10));

        // --- Painel Superior (Formulário e Ações) ---
        JPanel pnlSuperior = new JPanel(new BorderLayout(10, 10));

        // Formulário (GridLayout 2x2)
        JPanel pnlFormulario = new JPanel(new GridLayout(2, 2, 5, 5));
        txtId = new JTextField(5);
        txtId.setEditable(false); // ID somente leitura
        txtDescricao = new JTextField(20);

        pnlFormulario.add(new JLabel("ID:"));
        pnlFormulario.add(txtId);
        pnlFormulario.add(new JLabel("Descrição:"));
        pnlFormulario.add(txtDescricao);

        // Botões de Ação (FlowLayout)
        JPanel pnlBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnLimpar = new JButton("Limpar Campos");
        pnlBotoes.add(btnSalvar);
        pnlBotoes.add(btnExcluir);
        pnlBotoes.add(btnLimpar);

        // Adiciona Formulário e Botões ao Painel Superior
        pnlSuperior.add(pnlFormulario, BorderLayout.NORTH);
        pnlSuperior.add(pnlBotoes, BorderLayout.CENTER);
        add(pnlSuperior, BorderLayout.NORTH);


        // --- Pesquisa e Listagem (Centro) ---
        JPanel pnlListagem = new JPanel(new BorderLayout(5, 5));

        // Painel de Pesquisa
        JPanel pnlPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField txtPesquisa = new JTextField(15);
        JButton btnPesquisar = new JButton("Pesquisar");
        pnlPesquisa.add(new JLabel("Buscar Descrição:"));
        pnlPesquisa.add(txtPesquisa);
        pnlPesquisa.add(btnPesquisar);
        pnlListagem.add(pnlPesquisa, BorderLayout.NORTH);

        // Tabela de Resultados
        String[] colunas = {"ID", "Descrição"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaPrioridades = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaPrioridades);
        pnlListagem.add(scrollPane, BorderLayout.CENTER);

        add(pnlListagem, BorderLayout.CENTER);
    }

    // Ações para os botões (apenas placeholders)
    // Os métodos salvar(), alterar(), excluir() e pesquisar() seriam implementados aqui.
}