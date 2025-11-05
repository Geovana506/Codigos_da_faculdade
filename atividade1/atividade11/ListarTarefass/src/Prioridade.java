import java.sql.*;

public class Prioridade {
    private int id;
    private String descricao;

    public Prioridade() {}
    public Prioridade(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public boolean salvar() {
        String sql = "INSERT INTO prioridade (id, descricao) VALUES (?,?)";
        try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, descricao);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro salvar prioridade: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar() {
        String sql = "UPDATE prioridade SET descricao=? WHERE id=?";
        try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, descricao);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro alterar prioridade: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir() {
        String sql = "DELETE FROM prioridade WHERE id=?";
        try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro excluir prioridade: " + e.getMessage());
            return false;
        }
    }

    public boolean pesquisar() {
        String sql = "SELECT * FROM prioridade WHERE id=?";
        try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                descricao = rs.getString("descricao");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro pesquisar prioridade: " + e.getMessage());
        }
        return false;
    }

    // Getters/Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
