package dao;

import model.Produtor;
import util.Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutorDAO {

    public List<Produtor> listarProdutores() {
        List<Produtor> lista = new ArrayList<>();

        try {
            Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produtor");

            while (rs.next()) {
                Produtor p = new Produtor();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCidade(rs.getString("cidade"));

                lista.add(p);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}