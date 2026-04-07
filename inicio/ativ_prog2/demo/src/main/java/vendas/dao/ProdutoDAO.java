package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vendas.model.Produto;
import vendas.util.Conexao;

public class ProdutoDAO {

    public boolean salvar(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, qtde_estoque, id_categoria) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setDouble(3, produto.getQtdeEstoque());
            // Pegando o ID da Categoria que está anexada na ficha do Produto
            stmt.setInt(4, produto.getCategoria().getId()); 

            stmt.executeUpdate();
            System.out.println("SUCESSO: Produto '" + produto.getNome() + "' salvo no banco!");
            return true;

        } catch (SQLException e) {
            System.err.println("ERRO ao salvar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, qtde_estoque = ?, id_categoria = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setDouble(3, produto.getQtdeEstoque());
            stmt.setInt(4, produto.getCategoria().getId());
            stmt.setInt(5, produto.getId());

            int linhas = stmt.executeUpdate();
            if (linhas > 0) {
                System.out.println("SUCESSO: Produto alterado/estoque atualizado no banco!");
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("ERRO ao alterar produto: " + e.getMessage());
            return false;
        }
    }

    public boolean pesquisar(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- PRODUTO ENCONTRADO ---");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Estoque: " + rs.getDouble("qtde_estoque"));
                System.out.println("--------------------------\n");
                return true;
            } else {
                System.out.println("Produto não encontrado.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("ERRO ao pesquisar produto: " + e.getMessage());
            return false;
        }
    }
    
    public boolean excluir(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("SUCESSO: Produto excluído com sucesso!");
                return true;
            } else {
                System.out.println("AVISO: Nenhum produto encontrado com este ID para excluir.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("ERRO ao excluir produto: " + e.getMessage());
            return false;
        }
    }

    public void listarTodos() {
        String sql = "SELECT id, nome, qtde_estoque FROM produto ORDER BY id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            var rs = stmt.executeQuery();

            System.out.println("\n--- ESTOQUE DE PRODUTOS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Nome: " + rs.getString("nome") + 
                                   " | Qtd: " + rs.getDouble("qtde_estoque"));
            }
            System.out.println("---------------------------");

        } catch (SQLException e) {
            System.err.println("ERRO ao listar produtos: " + e.getMessage());
        }
    }

    public boolean atualizarEstoque(int id, double novaQuantidade) {
        String sql = "UPDATE produto SET qtde_estoque = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, novaQuantidade);
            stmt.setInt(2, id);

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("ERRO ao atualizar estoque: " + e.getMessage());
            return false;
        }
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQtdeEstoque(rs.getDouble("qtde_estoque"));
                p.setPreco(rs.getDouble("preco"));
                vendas.model.Categoria cat = new vendas.model.Categoria();
                cat.setId(rs.getInt("id_categoria"));
                p.setCategoria(cat);
                return p;
            }
        } catch (java.sql.SQLException e) {
            System.err.println("ERRO ao buscar produto: " + e.getMessage());
        }
        return null; 
    }

}