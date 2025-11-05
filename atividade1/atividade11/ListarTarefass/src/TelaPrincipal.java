import java.awt.*;
import javax.swing.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        super("Sistema de Gerenciamento de Tarefas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null); // Centraliza a tela

        // Layout
        setLayout(new GridLayout(4, 1, 10, 10)); // 4 linhas, 1 coluna, espaçamento de 10

        // Título
        JLabel titulo = new JLabel("Dashboard de Acesso", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo);

        // Botões de Navegação
        JButton btnTarefas = new JButton("Gerenciar Tarefas");
        JButton btnPrioridades = new JButton("Gerenciar Prioridades");
        JButton btnResponsaveis = new JButton("Gerenciar Responsáveis");

        // Adiciona a funcionalidade de abrir as outras telas
        btnTarefas.addActionListener(e -> new TelaListaTarefas().setVisible(true));
        btnPrioridades.addActionListener(e -> new TelaPrioridade().setVisible(true));
        btnResponsaveis.addActionListener(e -> new TelaResponsavel().setVisible(true));

        add(btnTarefas);
        add(btnPrioridades);
        add(btnResponsaveis);
    }

    public static void main(String[] args) {
        // Garante que a UI seja criada na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}