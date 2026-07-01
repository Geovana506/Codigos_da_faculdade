package controle.estoque.controller;

import controle.estoque.dao.VendaDAO;
import controle.estoque.model.Venda;

public class VendaController {
    VendaDAO vendaDAO = new VendaDAO();

    public boolean salvar(Venda venda) {
        return vendaDAO.salvar(venda);
    }

    public boolean alterar(Venda venda) {
        return vendaDAO.alterar(venda);
    }

    public boolean excluir(int id) {
        return vendaDAO.excluir(id);
    }

    public Venda pesquisar(int id) {
        return vendaDAO.pesquisar(id);
    }
}
