package dao;

import model.Periodo;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PeriodoDAO {

    private static final Logger logger =
            Logger.getLogger(PeriodoDAO.class.getName());

    public boolean salvar(Periodo p) {
        String sql = "INSERT INTO periodo (nome_periodo) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNomePeriodo());
            ps.executeUpdate();
            logger.info("Período salvo");
            return true;

        } catch (Exception e) {
            logger.severe("Erro ao salvar período");
            return false;
        }
    }

    public List<Periodo> listar() {
        List<Periodo> lista = new ArrayList<>();
        String sql = "SELECT * FROM periodo";

        try (Connection conn = Conexao.conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Periodo p = new Periodo();
                p.setId(rs.getInt("id"));
                p.setNomePeriodo(rs.getString("nome_periodo"));
                lista.add(p);
            }

        } catch (Exception e) {
            logger.severe("Erro ao listar períodos");
        }

        return lista;
    }
}
