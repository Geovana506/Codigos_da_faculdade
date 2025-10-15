import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormCadastroEditora extends JFrame {

    // Componentes da interface
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtLocalidade;
    private JButton btnSalvar;
    private JButton btnLimpar;
    private JButton btnSair;

    /**
     * Construtor da classe FormCadastroEditora.
     * Configura a janela e inicializa os componentes.
     */
    public FormCadastroEditora() {
        // Configurações da janela
        setTitle("Cadastro de Editora");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250); // Tamanho ajustado para menos campos
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

        // Campo Localidade
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        painelPrincipal.add(new JLabel("Localidade:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtLocalidade = new JTextField(15);
        painelPrincipal.add(txtLocalidade, gbc);

        // --- Painel de Botões ---

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Centralizado
        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");

        // Adicionar Listeners aos botões
        btnSalvar.addActionListener(e -> salvarEditora());
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
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa duas colunas
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painelPrincipal.add(painelBotoes, gbc);

        // Adicionar painel principal à janela
        add(painelPrincipal);
    }

    /**
     * Valida os campos, cria o objeto Editora e salva no arquivo.
     */
    private void salvarEditora() {
        String idStr = txtId.getText().trim();
        String nome = txtNome.getText().trim();
        String localidade = txtLocalidade.getText().trim();

        // 1. Validação de campos obrigatórios
        if (idStr.isEmpty() || nome.isEmpty() || localidade.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos: ID, Nome e Localidade.",
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
            // Cria o objeto Editora
            Editora editora = new Editora();
            editora.setId(id);
            editora.setNome(nome);
            editora.setLocalidade(localidade); // Adicionar o setter em Editora se necessário

            // Simula o salvamento (como no FormCadastroLivro, chamando o método salvar
            // e depois o arquivo)
            editora.salvar();

            salvarNoArquivo(editora);

            JOptionPane.showMessageDialog(this,
                    "Editora cadastrada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparCampos();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar dados no arquivo 'editoras.txt': " + e.getMessage(),
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
     * Escreve os dados da editora em um arquivo de texto.
     * @param editora O objeto Editora a ser salvo.
     * @throws IOException Se ocorrer um erro de I/O ao escrever no arquivo.
     */
    private void salvarNoArquivo(Editora editora) throws IOException {
        // Obter data e hora atual
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataHora = agora.format(formatter);

        // Criar o conteúdo a ser salvo
        String conteudo = String.format("""
                === EDITORA CADASTRADA ===
                Data/Hora: %s
                ID: %d
                Nome: %s
                Localidade: %s
                ==========================

                """,
                dataHora,
                editora.getId(),
                editora.getNome(),
                editora.getLocalidade()); // Assume que existe o método getLocalidade()

        try (FileWriter writer = new FileWriter("editoras.txt", true)) {
            writer.write(conteudo);
        }
    }

    /**
     * Limpa todos os campos do formulário e foca no campo ID.
     */
    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtLocalidade.setText("");
        txtId.requestFocus(); // Focar no primeiro campo
    }

    /**
     * Método main para testar o formulário.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormCadastroEditora::new);
    }
}

// --------------------------------------------------

// Extensão e adaptação da classe Editora para o formulário funcionar

/*
 * Observação: Para que o código acima funcione corretamente,
 * a classe Editora precisa ter o campo 'localidade' e os métodos
 * 'getId()', 'getNome()', 'getLocalidade()', 'setId(int)', 'setNome(String)'
 * e 'setLocalidade(String)'.
 *
 * Se você ainda não tem, aqui está a sugestão de como a classe Editora deve ser:
 */

/**
 * Classe Editora, adaptada para suportar o cadastro de Localidade.
 * Assume a existência de uma classe ClasseGenerica com os métodos getId, getNome, etc.
 */
// Supondo que você tenha uma ClasseGenerica abstrata ou interface
// public abstract class ClasseGenerica {
//     protected int id;
//     protected String nome;
//     public int getId() { return id; }
//     public void setId(int id) { this.id = id; }
//     public String getNome() { return nome; }
//     public void setNome(String nome) { this.nome = nome; }
//     public abstract void salvar();
// }

/* A classe Editora completa, estendendo ClasseGenerica (suposta): */
class Editora extends EditoraBase { // Renomeei para evitar conflito na mesma resposta, use 'ClasseGenerica' se for o nome real
    private String localidade;

    // Construtor padrão (necessário ou útil para criar o objeto vazio e preencher)
    public Editora() {
        super();
    }
    
    // Construtor com todos os campos (útil, mas opcional)
    public Editora(int id, String nome, String localidade) {
        this.id = id; // Assume que id está acessível (protected ou via setter)
        this.nome = nome; // Assume que nome está acessível (protected ou via setter)
        this.localidade = localidade;
    }
    
    // Getters e Setters
    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Override
    public void salvar() {
        System.out.println("Editora " + getNome() + " (ID: " + getId() + ") salva com sucesso no TXT!");
        // Aqui você pode adicionar lógica de persistência mais robusta, como em um banco de dados
    }

    // Adaptando os getters/setters da EditoraBase para o contexto
    @Override
    public int getId() {
        return super.getId();
    }
    
    @Override
    public void setId(int id) {
        super.setId(id);
    }
    
    @Override
    public String getNome() {
        return super.getNome();
    }
    
    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }
}

// Classe base para a Editora (simulando a ClasseGenerica com campos básicos)
abstract class EditoraBase {
    protected int id;
    protected String nome;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public abstract void salvar();
}