package dao;
import model.Aluno;
import util.Conexao;

import java.sql.*;
import java.util.logging.Logger;

public class AlunoDAO {

    private static final Logger logger =
            Logger.getLogger(AlunoDAO.class.getName());

    public boolean salvar(Aluno aluno) {

        if (!aluno.validarMatricula()) {
            throw new IllegalArgumentException("Matrícula inválida");
        }

        String sqlPessoa =
                "INSERT INTO pessoa (nome, endereco, telefone, email) VALUES (?,?,?,?)";
        String sqlAluno =
                "INSERT INTO aluno (matricula, nome_pai, nome_mae, pessoa_id) VALUES (?,?,?,?)";

        try (Connection conn = Conexao.conectar()) {

            PreparedStatement psPessoa =
                    conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);

            psPessoa.setString(1, aluno.getNome());
            psPessoa.setString(2, aluno.getEndereco());
            psPessoa.setString(3, aluno.getTelefone());
            psPessoa.setString(4, aluno.getEmail());
            psPessoa.executeUpdate();

            ResultSet rs = psPessoa.getGeneratedKeys();
            rs.next();
            int pessoaId = rs.getInt(1);

            PreparedStatement psAluno =
                    conn.prepareStatement(sqlAluno);

            psAluno.setString(1, aluno.getMatricula());
            psAluno.setString(2, aluno.getNomePai());
            psAluno.setString(3, aluno.getNomeMae());
            psAluno.setInt(4, pessoaId);
            psAluno.executeUpdate();

            logger.info("Aluno salvo com sucesso");
            return true;

        } catch (Exception e) {
            logger.severe("Erro ao salvar aluno");
            return false;
        }
    }
}
