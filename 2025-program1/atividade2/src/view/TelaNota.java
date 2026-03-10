package view;

import model.Nota;
import dao.NotaDAO;
import model.Diario;
import dao.DiarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaNota extends JFrame {

    private JTextField txtNota, txtDiarioId;
    private JButton btnSalvar, btnCancelar;

    public TelaNota() {
        setTitle("Cadastrar Nota");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Nota:"));
        txtNota = new JTextField();
        panel.add(txtNota);

        panel.add(new JLabel("ID do Diário:"));
        txtDiarioId = new JTextField();
        panel.add(txtDiarioId);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarNota();
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

    private void salvarNota() {
        try {
            double notaValue = Double.parseDouble(txtNota.getText());
            int diarioId = Integer.parseInt(txtDiarioId.getText());

            Nota nota = new Nota();
            nota.setNota(notaValue);

            NotaDAO dao = new NotaDAO();
            if (dao.salvar(nota, diarioId)) {
                JOptionPane.showMessageDialog(this, "Nota salva com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar nota.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}