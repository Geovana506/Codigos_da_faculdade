package view;

import model.Disciplina;
import dao.DisciplinaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDisciplina extends JFrame {

    private JTextField txtNomeDisciplina;
    private JButton btnSalvar, btnCancelar;

    public TelaDisciplina() {
        setTitle("Cadastrar Disciplina");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Nome da Disciplina:"));
        txtNomeDisciplina = new JTextField();
        panel.add(txtNomeDisciplina);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDisciplina();
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

    private void salvarDisciplina() {
        try {
            Disciplina disciplina = new Disciplina();
            disciplina.setNomeDisciplina(txtNomeDisciplina.getText());

            DisciplinaDAO dao = new DisciplinaDAO();
            if (dao.salvar(disciplina)) {
                JOptionPane.showMessageDialog(this, "Disciplina salva com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar disciplina.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}