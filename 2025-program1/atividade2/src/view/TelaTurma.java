package view;

import model.Turma;
import dao.TurmaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaTurma extends JFrame {

    private JTextField txtNomeTurma;
    private JButton btnSalvar, btnCancelar;

    public TelaTurma() {
        setTitle("Cadastrar Turma");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Nome da Turma:"));
        txtNomeTurma = new JTextField();
        panel.add(txtNomeTurma);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarTurma();
            }
        });
        panel.add(btnSalvar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(btnCancelar);

        add(panel);
    }

    private void salvarTurma() {
        try {
            Turma turma = new Turma();
            turma.setNomeTurma(txtNomeTurma.getText());

            TurmaDAO dao = new TurmaDAO();
            if (dao.salvar(turma)) {
                JOptionPane.showMessageDialog(this, "Turma salva com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar turma.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}