package dao;

import model.Professor;
import util.Conexao;

import java.sql.*;
import java.util.logging.Logger;

public class ProfessorDAO {

    private static final Logger logger =
            Logger.getLogger(ProfessorDAO.class.getName());

    public boolean salvar(Professor prof) {

        String sqlPessoa =
                "INSERT INTO pessoa (nome, endereco, telefone, email) VALUES (?,?,?,?)";
        String sqlProfessor =
                "INSERT INTO professor (matricula, pessoa_id) VALUES (?,?)";

        try (Connection conn = Conexao.conectar()) {

            PreparedStatement psPessoa =
                    conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);

            psPessoa.setString(1, prof.getNome());
            psPessoa.setString(2, prof.getEndereco());
            psPessoa.setString(3, prof.getTelefone());
            psPessoa.setString(4, prof.getEmail());
            psPessoa.executeUpdate();

            ResultSet rs = psPessoa.getGeneratedKeys();
            rs.next();
            int pessoaId = rs.getInt(1);

            PreparedStatement psProf =
                    conn.prepareStatement(sqlProfessor);

            psProf.setString(1, prof.getMatricula());
            psProf.setInt(2, pessoaId);
            psProf.executeUpdate();

            logger.info("Professor salvo");
            return true;

        } catch (Exception e) {
            logger.severe("Erro ao salvar professor");
            return false;
        }
    }
}
