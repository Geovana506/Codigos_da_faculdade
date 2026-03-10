package dao;

import model.Turma;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TurmaDAO {

    private static final Logger logger =
            Logger.getLogger(TurmaDAO.class.getName());

    public boolean salvar(Turma turma) {
        String sql = "INSERT INTO turma (nome_turma) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, turma.getNomeTurma());
            ps.executeUpdate();

            logger.info("Turma salva com sucesso");
            return true;

        } catch (Exception e) {
            logger.severe("Erro ao salvar turma");
            return false;
        }
    }

    public boolean alterar(Turma turma) {
        String sql = "UPDATE turma SET nome_turma=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, turma.getNomeTurma());
            ps.setInt(2, turma.getId());
            ps.executeUpdate();

            logger.info("Turma alterada com sucesso");
            return true;

        } catch (Exception e) {
            logger.severe("Erro ao alterar turma");
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM turma WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            logger.info("Turma excluída com sucesso");
            return true;

        } catch (Exception e) {
            logger.severe("Erro ao excluir turma");
            return false;
        }
    }

    public List<Turma> listar() {
        List<Turma> lista = new ArrayList<>();
        String sql = "SELECT * FROM turma";

        try (Connection conn = Conexao.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Turma t = new Turma();
                t.setId(rs.getInt("id"));
                t.setNomeTurma(rs.getString("nome_turma"));
                lista.add(t);
            }

            logger.info("Turmas listadas com sucesso");

        } catch (Exception e) {
            logger.severe("Erro ao listar turmas");
        }

        return lista;
    }
}
