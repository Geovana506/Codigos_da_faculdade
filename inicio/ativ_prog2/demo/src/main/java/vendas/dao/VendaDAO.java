package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vendas.model.Produto;
import vendas.model.Venda;
import vendas.util.Conexao;

public class VendaDAO {

    public int contarVendasPorCliente(int idCliente) {
        String sql = "SELECT COUNT(*) AS total FROM venda WHERE id_cliente = ?";
        
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao contar vendas: " + e.getMessage());
        }
        return 0;
    }

    public boolean salvar(Venda venda, Produto produto, double quantidade) {
        String sqlVenda = "INSERT INTO venda (data_venda, valor_total, id_cliente) VALUES (?, ?, ?)";
        String sqlVendaProduto = "INSERT INTO venda_produto (id_venda, id_produto, quantidade) VALUES (?, ?, ?)";

        Connection conn = null;

        try {
            conn = Conexao.conectar();
            conn.setAutoCommit(false);
            PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
            stmtVenda.setDate(1, new java.sql.Date(venda.getDataVenda().getTime())); 
            stmtVenda.setDouble(2, venda.getValorTotal());
            stmtVenda.setInt(3, venda.getCliente().getId());
            stmtVenda.executeUpdate();

           
            ResultSet rs = stmtVenda.getGeneratedKeys();
            int idVendaGerado = 0;
            if (rs.next()) {
                idVendaGerado = rs.getInt(1);
            }

           
            PreparedStatement stmtItem = conn.prepareStatement(sqlVendaProduto);
            stmtItem.setInt(1, idVendaGerado); 
            stmtItem.setInt(2, produto.getId());
            stmtItem.setDouble(3, quantidade);
            stmtItem.executeUpdate();

            
            conn.commit();
            System.out.println("SUCESSO: Venda registrada no banco de dados com ID: " + idVendaGerado);
            return true;

        } catch (SQLException e) {
            System.err.println("ERRO ao salvar venda. Desfazendo operações... " + e.getMessage());
            try {
                if (conn != null) conn.rollback(); 
            } catch (SQLException ex) {
                System.err.println("Erro crítico no rollback: " + ex.getMessage());
            }
            return false;
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}