package controle.estoque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import controle.estoque.model.Fornecedor;
import controle.estoque.model.FornecedorProduto;
import controle.estoque.model.Produto;

public class FornecedorProdutoDAO {
    public boolean salvar(FornecedorProduto fornecedorProduto) {
        String sql = "INSERT INTO fornecedor_produto (fornecedor_id, produto_id) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, fornecedorProduto.getFornecedor().getId());
            stmt.setInt(2, fornecedorProduto.getProduto().getId());
            int linhas = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                fornecedorProduto.setId(rs.getInt(1));
            }
            return linhas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(FornecedorProduto fornecedorProduto) {
        String sql = "UPDATE fornecedor_produto SET fornecedor_id = ?, produto_id = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fornecedorProduto.getFornecedor().getId());
            stmt.setInt(2, fornecedorProduto.getProduto().getId());
            stmt.setInt(3, fornecedorProduto.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM fornecedor_produto WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public FornecedorProduto pesquisar(int id) {
        String sql = "SELECT fp.id AS fornecedor_produto_id, f.id AS fornecedor_id, f.nome_fantasia, f.razao_social, f.cnpj, "
                + "p.*, c.nome AS categoria_nome FROM fornecedor_produto fp "
                + "INNER JOIN fornecedor f ON f.id = fp.fornecedor_id "
                + "INNER JOIN produto p ON p.id = fp.produto_id "
                + "INNER JOIN categoria c ON c.id = p.categoria_id WHERE fp.id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(rs.getInt("fornecedor_id"), rs.getString("nome_fantasia"),
                        rs.getString("razao_social"), rs.getString("cnpj"));
                Produto produto = ProdutoDAO.montarProduto(rs);
                return new FornecedorProduto(rs.getInt("fornecedor_produto_id"), fornecedor, produto);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
