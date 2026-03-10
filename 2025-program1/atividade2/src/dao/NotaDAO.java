package dao;

import model.Nota;
import util.Conexao;

import java.sql.*;
import java.util.logging.Logger;

public class NotaDAO {

    private static final Logger logger =
            Logger.getLogger(NotaDAO.class.getName());

    public boolean salvar(Nota n, int diarioId) {

        if (n.getNota() < 0 || n.getNota() > 10) {
            throw new IllegalArgumentException("Nota inválida");
        }

        String sql = "INSERT INTO nota (nota, diario_id) VALUES (?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, n.getNota());
            ps.setInt(2, diarioId);
            ps.executeUpdate();

            logger.info("Nota salva");
            return true;

        } catch (Exception e) {
            logger.severe("Erro ao salvar nota");
            return false;
        }
    }
}
