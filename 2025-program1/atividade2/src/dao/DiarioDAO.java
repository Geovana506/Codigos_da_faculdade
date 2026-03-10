package dao;
import model.Diario;
import util.Conexao;

import java.sql.*;
import java.util.logging.Logger;

public class DiarioDAO {

    private static final Logger logger =
            Logger.getLogger(DiarioDAO.class.getName());

    public boolean salvar(Diario d) {

        if (d.getAluno() == null || d.getDisciplina() == null ||
            d.getTurma() == null || d.getPeriodo() == null) {

            throw new IllegalArgumentException("Diário incompleto");
        }

        String sql = """
            INSERT INTO diario (status, aluno_id, disciplina_id, turma_id, periodo_id)
            VALUES (?,?,?,?,?)
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, d.isStatus());
            ps.setInt(2, d.getAluno().getId());
            ps.setInt(3, d.getDisciplina().getId());
            ps.setInt(4, d.getTurma().getId());
            ps.setInt(5, d.getPeriodo().getId());

            ps.executeUpdate();
            logger.info("Diário salvo");
            return true;

        } catch (Exception e) {
            logger.severe("Erro ao salvar diário");
            return false;
        }
    }
}
