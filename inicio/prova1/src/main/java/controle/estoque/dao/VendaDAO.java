package controle.estoque.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import controle.estoque.model.Venda;
import controle.estoque.model.VendaProduto;

public class VendaDAO {
    public boolean salvar(Venda venda) {
        String sqlVenda = "INSERT INTO venda (cliente_id, data_venda, valor_total) VALUES (?, ?, ?)";
        String sqlItem = "INSERT INTO venda_produto (venda_id, produto_id, quantidade, valor_unitario) VALUES (?, ?, ?, ?)";
        String sqlProduto = "UPDATE produto SET qtde_estoque = qtde_estoque - ?, valor_ultima_venda = ? WHERE id = ?";

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            System.out.println("Venda precisa ter pelo menos um produto.");
            return false;
        }

        try (Connection conn = Conexao.getConnection()) {
            conn.setAutoCommit(false);
            try {
                if (contarVendasDoCpfNoMes(conn, venda.getCliente().getCpf(), venda.getDataVenda()) >= 3) {
                    System.out.println("Venda abortada: cliente ja possui 3 vendas neste mes.");
                    conn.rollback();
                    return false;
                }

                for (VendaProduto item : venda.getItens()) {
                    if (!existeEstoqueParaVenda(conn, item.getProduto().getId(), item.getQuantidade())) {
                        System.out.println("Venda abortada: estoque insuficiente para o produto "
                                + item.getProduto().getId());
                        conn.rollback();
                        return false;
                    }
                }

                venda.calcularValorTotal();
                PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
                stmtVenda.setInt(1, venda.getCliente().getId());
                stmtVenda.setDate(2, Date.valueOf(venda.getDataVenda()));
                stmtVenda.setDouble(3, venda.getValorTotal());
                stmtVenda.executeUpdate();
                ResultSet rs = stmtVenda.getGeneratedKeys();
                if (rs.next()) {
                    venda.setId(rs.getInt(1));
                }

                PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
                PreparedStatement stmtProduto = conn.prepareStatement(sqlProduto);
                for (VendaProduto item : venda.getItens()) {
                    stmtItem.setInt(1, venda.getId());
                    stmtItem.setInt(2, item.getProduto().getId());
                    stmtItem.setDouble(3, item.getQuantidade());
                    stmtItem.setDouble(4, item.getValorUnitario());
                    stmtItem.executeUpdate();

                    stmtProduto.setDouble(1, item.getQuantidade());
                    stmtProduto.setDouble(2, item.getValorUnitario());
                    stmtProduto.setInt(3, item.getProduto().getId());
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

    public boolean alterar(Venda venda) {
        String sql = "UPDATE venda SET cliente_id = ?, data_venda = ?, valor_total = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            venda.calcularValorTotal();
            stmt.setInt(1, venda.getCliente().getId());
            stmt.setDate(2, Date.valueOf(venda.getDataVenda()));
            stmt.setDouble(3, venda.getValorTotal());
            stmt.setInt(4, venda.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM venda WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Venda pesquisar(int id) {
        String sql = "SELECT v.*, c.nome, c.cpf, c.rg, c.endereco, c.telefone FROM venda v INNER JOIN cliente c ON c.id = v.cliente_id WHERE v.id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                venda.setDataVenda(rs.getDate("data_venda").toLocalDate());
                venda.setValorTotal(rs.getDouble("valor_total"));
                venda.setCliente(new controle.estoque.model.Cliente(rs.getInt("cliente_id"), rs.getString("nome"),
                        rs.getString("cpf"), rs.getString("rg"), rs.getString("endereco"), rs.getString("telefone")));
                return venda;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private int contarVendasDoCpfNoMes(Connection conn, String cpf, LocalDate dataVenda) throws Exception {
        String sql = "SELECT COUNT(*) FROM venda v INNER JOIN cliente c ON c.id = v.cliente_id "
                + "WHERE c.cpf = ? AND EXTRACT(MONTH FROM v.data_venda) = ? AND EXTRACT(YEAR FROM v.data_venda) = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        stmt.setInt(2, dataVenda.getMonthValue());
        stmt.setInt(3, dataVenda.getYear());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    private boolean existeEstoqueParaVenda(Connection conn, int produtoId, double quantidade) throws Exception {
        String sql = "SELECT qtde_estoque FROM produto WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, produtoId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            double estoque = rs.getDouble("qtde_estoque");
            return estoque >= 1 && estoque >= quantidade;
        }
        return false;
    }
}
