package dao;

import model.Disciplina;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DisciplinaDAO {

    private static final Logger logger =
            Logger.getLogger(DisciplinaDAO.class.getName());

    public boolean salvar(Disciplina d) {
        String sql = "INSERT INTO disciplina (nome_disciplina) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getNomeDisciplina());
            ps.executeUpdate();
            logger.info("Disciplina salva");
            return true;

        } catch (Exception e) {
            logger.severe("Erro ao salvar disciplina");
            return false;
        }
    }

    public List<Disciplina> listar() {
        List<Disciplina> lista = new ArrayList<>();
        String sql = "SELECT * FROM disciplina";

        try (Connection conn = Conexao.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Disciplina d = new Disciplina();
                d.setId(rs.getInt("id"));
                d.setNomeDisciplina(rs.getString("nome_disciplina"));
                lista.add(d);
            }

        } catch (Exception e) {
            logger.severe("Erro ao listar disciplinas");
        }

        return lista;
    }
}
