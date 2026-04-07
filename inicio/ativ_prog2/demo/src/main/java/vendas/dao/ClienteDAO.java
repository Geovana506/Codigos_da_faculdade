package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vendas.model.Cliente;
import vendas.util.Conexao;

public class ClienteDAO {

 
    public boolean salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, rg, endereco, telefone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getTelefone());
            stmt.executeUpdate();
            System.out.println("SUCESSO: Cliente '" + cliente.getNome() + "' salvo no banco de dados!");
            return true;

        } catch (SQLException e) {
            System.err.println("ERRO: Não foi possível salvar o cliente. " + e.getMessage());
            return false;
        }
    }

    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, rg = ?, endereco = ?, telefone = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getTelefone());
            stmt.setInt(6, cliente.getId()); // O ID vai no WHERE

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("SUCESSO: Cliente alterado com sucesso!");
                return true;
            } else {
                System.out.println("AVISO: Nenhum cliente encontrado com este ID para alterar.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("ERRO ao alterar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("SUCESSO: Cliente excluído com sucesso!");
                return true;
            } else {
                System.out.println("AVISO: Nenhum cliente encontrado com este ID para excluir.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("ERRO ao excluir cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean pesquisar(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);  
            var rs = stmt.executeQuery();

            if (rs.next()) { 
                System.out.println("\n--- CLIENTE ENCONTRADO ---");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("--------------------------\n");
                return true;
            } else {
                System.out.println("AVISO: Cliente não encontrado.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("ERRO ao pesquisar cliente: " + e.getMessage());
            return false;
        }
    }

    public void listarTodos() {
        String sql = "SELECT id, nome FROM cliente ORDER BY id"; // ORDER BY organiza do menor para o maior ID

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            var rs = stmt.executeQuery();

            System.out.println("\n--- LISTA DE CLIENTES CADASTRADOS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nome: " + rs.getString("nome"));
            }
            System.out.println("-------------------------------------");

        } catch (SQLException e) {
            System.err.println("ERRO ao listar clientes: " + e.getMessage());
        }
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                // O Controller só precisa do Nome para a regra do Flávio, mas poderíamos preencher tudo
                return c;
            }
        } catch (SQLException e) {
            System.err.println("ERRO ao buscar cliente: " + e.getMessage());
        }
        return null;
    }

}