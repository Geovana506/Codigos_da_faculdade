import java.sql.*;

public class Responsavel {
    private int id;
    private String nome;

    public Responsavel() {}
    public Responsavel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public boolean salvar() {
        String sql = "INSERT INTO responsavel (id, nome) VALUES (?,?)";
        try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro salvar responsavel: " + e.getMessage());
            return false;
        }
    }

    public boolean alterar() {
        String sql = "UPDATE responsavel SET nome=? WHERE id=?";
        try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro alterar responsavel: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir() {
        String sql = "DELETE FROM responsavel WHERE id=?";
        try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro excluir responsavel: " + e.getMessage());
            return false;
        }
    }

    public boolean pesquisar() {
        String sql = "SELECT * FROM responsavel WHERE id=?";
        try (Connection con = Conexao.conectar(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nome = rs.getString("nome");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro pesquisar responsavel: " + e.getMessage());
        }
        return false;
    }

    // Getters/Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
