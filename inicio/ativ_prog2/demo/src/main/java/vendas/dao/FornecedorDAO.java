package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vendas.model.Fornecedor;
import vendas.util.Conexao;

public class FornecedorDAO {

    public boolean salvar(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (razao_social, cnpj, nome_fantasia) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getRazaoSocial());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getNomeFantasia());

            stmt.executeUpdate();
            System.out.println("SUCESSO: Fornecedor '" + fornecedor.getNomeFantasia() + "' salvo no banco!");
            return true;

        } catch (SQLException e) {
            System.err.println("ERRO ao salvar fornecedor: " + e.getMessage());
            return false;
        }
    }

    public void listarTodos() {
        String sql = "SELECT id, razao_social, cnpj, nome_fantasia FROM fornecedor ORDER BY id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            var rs = stmt.executeQuery();

            System.out.println("\n--- LISTA DE FORNECEDORES ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + 
                                   " | Razão Social: " + rs.getString("razao_social") + 
                                   " | Fantasia: " + rs.getString("nome_fantasia") + 
                                   " | CNPJ: " + rs.getString("cnpj"));
            }
            System.out.println("-----------------------------");

        } catch (SQLException e) {
            System.err.println("ERRO ao listar fornecedores: " + e.getMessage());
        }
    }
}