package controle.estoque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import controle.estoque.model.Categoria;
import controle.estoque.model.Produto;

public class ProdutoDAO {
    public boolean salvar(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco_medio, qtde_estoque, valor_ultima_compra, valor_ultima_venda, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preencherStatement(produto, stmt);
            int linhas = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                produto.setId(rs.getInt(1));
            }
            return linhas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco_medio = ?, qtde_estoque = ?, valor_ultima_compra = ?, valor_ultima_venda = ?, categoria_id = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            preencherStatement(produto, stmt);
            stmt.setInt(7, produto.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Produto pesquisar(int id) {
        String sql = "SELECT p.*, c.nome AS categoria_nome FROM produto p INNER JOIN categoria c ON c.id = p.categoria_id WHERE p.id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return montarProduto(rs);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean existeEstoqueParaVenda(int produtoId, double quantidade) {
        Produto produto = pesquisar(produtoId);
        return produto != null && produto.getQtdeEstoque() >= 1 && produto.getQtdeEstoque() >= quantidade;
    }

    private void preencherStatement(Produto produto, PreparedStatement stmt) throws Exception {
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPrecoMedio());
        stmt.setDouble(3, produto.getQtdeEstoque());
        stmt.setDouble(4, produto.getValorUltimaCompra());
        stmt.setDouble(5, produto.getValorUltimaVenda());
        stmt.setInt(6, produto.getCategoria().getId());
    }

    static Produto montarProduto(ResultSet rs) throws Exception {
        Categoria categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("categoria_nome"));
        return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco_medio"),
                rs.getDouble("qtde_estoque"), rs.getDouble("valor_ultima_compra"),
                rs.getDouble("valor_ultima_venda"), categoria);
    }
}
