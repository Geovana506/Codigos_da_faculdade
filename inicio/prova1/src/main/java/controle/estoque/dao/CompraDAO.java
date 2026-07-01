package controle.estoque.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import controle.estoque.model.Compra;
import controle.estoque.model.Fornecedor;
import controle.estoque.model.CompraProduto;

public class CompraDAO {
    public boolean salvar(Compra compra) {
        String sqlCompra = "INSERT INTO compra (fornecedor_id, data_compra, valor_total) VALUES (?, ?, ?)";
        String sqlItem = "INSERT INTO compra_produto (compra_id, produto_id, quantidade, valor_unitario) VALUES (?, ?, ?, ?)";
        String sqlProduto = "UPDATE produto SET qtde_estoque = qtde_estoque + ?, valor_ultima_compra = ?, preco_medio = ? WHERE id = ?";

        if (compra.getItens() == null || compra.getItens().isEmpty()) {
            System.out.println("Compra precisa ter pelo menos um produto.");
            return false;
        }

        try (Connection conn = Conexao.getConnection()) {
            conn.setAutoCommit(false);
            try {
                compra.calcularValorTotal();
                PreparedStatement stmtCompra = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS);
                stmtCompra.setInt(1, compra.getFornecedor().getId());
                stmtCompra.setDate(2, Date.valueOf(compra.getDataCompra()));
                stmtCompra.setDouble(3, compra.getValorTotal());
                stmtCompra.executeUpdate();
                ResultSet rs = stmtCompra.getGeneratedKeys();
                if (rs.next()) {
                    compra.setId(rs.getInt(1));
                }

                PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
                PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto);
                for (CompraProduto item : compra.getItens()) {
                    double novoPrecoMedio = calcularPrecoMedioComNovaCompra(conn, item.getProduto().getId(),
                            item.getValorUnitario());

                    stmtItem.setInt(1, compra.getId());
                    stmtItem.setInt(2, item.getProduto().getId());
                    stmtItem.setDouble(3, item.getQuantidade());
                    stmtItem.setDouble(4, item.getValorUnitario());
                    stmtItem.executeUpdate();

                    stmtProduto.setDouble(1, item.getQuantidade());
                    stmtProduto.setDouble(2, item.getValorUnitario());
                    stmtProduto.setDouble(3, novoPrecoMedio);
                    stmtProduto.setInt(4, item.getProduto().getId());
                    stmtProduto.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Compra compra) {
        String sql = "UPDATE compra SET fornecedor_id = ?, data_compra = ?, valor_total = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            compra.calcularValorTotal();
            stmt.setInt(1, compra.getFornecedor().getId());
            stmt.setDate(2, Date.valueOf(compra.getDataCompra()));
            stmt.setDouble(3, compra.getValorTotal());
            stmt.setInt(4, compra.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM compra WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Compra pesquisar(int id) {
        String sql = "SELECT c.*, f.nome_fantasia, f.razao_social, f.cnpj FROM compra c INNER JOIN fornecedor f ON f.id = c.fornecedor_id WHERE c.id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setDataCompra(rs.getDate("data_compra").toLocalDate());
                compra.setValorTotal(rs.getDouble("valor_total"));
                compra.setFornecedor(new Fornecedor(rs.getInt("fornecedor_id"), rs.getString("nome_fantasia"),
                        rs.getString("razao_social"), rs.getString("cnpj")));
                return compra;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private double calcularPrecoMedioComNovaCompra(Connection conn, int produtoId, double valorUnitarioCompra)
            throws Exception {
        String sql = "SELECT COUNT(*) AS total, AVG(valor_unitario) AS media FROM compra_produto WHERE produto_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, produtoId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next() && rs.getInt("total") > 0) {
            int totalCompras = rs.getInt("total");
            double mediaAtual = rs.getDouble("media");
            return ((mediaAtual * totalCompras) + valorUnitarioCompra) / (totalCompras + 1);
        }
        return valorUnitarioCompra;
    }
}
