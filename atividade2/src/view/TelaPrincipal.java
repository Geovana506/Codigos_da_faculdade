package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema de Gerenciamento Escolar");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();

        // Menu Aluno
        JMenu menuAluno = new JMenu("Aluno");
        JMenuItem itemCadastrarAluno = new JMenuItem("Cadastrar Aluno");
        itemCadastrarAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaAluno().setVisible(true);
            }
        });
        menuAluno.add(itemCadastrarAluno);
        menuBar.add(menuAluno);

        // Menu Disciplina
        JMenu menuDisciplina = new JMenu("Disciplina");
        JMenuItem itemCadastrarDisciplina = new JMenuItem("Cadastrar Disciplina");
        itemCadastrarDisciplina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaDisciplina().setVisible(true);
            }
        });
        menuDisciplina.add(itemCadastrarDisciplina);
        menuBar.add(menuDisciplina);

        // Menu Turma
        JMenu menuTurma = new JMenu("Turma");
        JMenuItem itemCadastrarTurma = new JMenuItem("Cadastrar Turma");
        itemCadastrarTurma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaTurma().setVisible(true);
            }
        });
        menuTurma.add(itemCadastrarTurma);
        menuBar.add(menuTurma);

        // Menu Nota
        JMenu menuNota = new JMenu("Nota");
        JMenuItem itemCadastrarNota = new JMenuItem("Cadastrar Nota");
        itemCadastrarNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaNota().setVisible(true);
            }
        });
        menuNota.add(itemCadastrarNota);
        menuBar.add(menuNota);

        setJMenuBar(menuBar);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Bem-vindo ao Sistema Escolar", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);
        add(panel);
    }
}