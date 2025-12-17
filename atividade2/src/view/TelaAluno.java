package view;

import model.Aluno;
import dao.AlunoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAluno extends JFrame {

    private JTextField txtNome, txtEndereco, txtTelefone, txtEmail, txtMatricula, txtNomePai, txtNomeMae;
    private JButton btnSalvar, btnCancelar;

    public TelaAluno() {
        setTitle("Cadastrar Aluno");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2));

        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        panel.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        panel.add(txtEndereco);

        panel.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        panel.add(txtTelefone);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Matrícula:"));
        txtMatricula = new JTextField();
        panel.add(txtMatricula);

        panel.add(new JLabel("Nome do Pai:"));
        txtNomePai = new JTextField();
        panel.add(txtNomePai);

        panel.add(new JLabel("Nome da Mãe:"));
        txtNomeMae = new JTextField();
        panel.add(txtNomeMae);

        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAluno();
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

    private void salvarAluno() {
        try {
            Aluno aluno = new Aluno();
            aluno.setNome(txtNome.getText());
            aluno.setEndereco(txtEndereco.getText());
            aluno.setTelefone(txtTelefone.getText());
            aluno.setEmail(txtEmail.getText());
            aluno.setMatricula(txtMatricula.getText());
            aluno.setNomePai(txtNomePai.getText());
            aluno.setNomeMae(txtNomeMae.getText());

            AlunoDAO dao = new AlunoDAO();
            if (dao.salvar(aluno)) {
                JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar aluno.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}