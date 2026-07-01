package controle.estoque.controller;

import controle.estoque.dao.CompraDAO;
import controle.estoque.model.Compra;

public class CompraController {
    CompraDAO compraDAO = new CompraDAO();

    public boolean salvar(Compra compra) {
        return compraDAO.salvar(compra);
    }

    public boolean alterar(Compra compra) {
        return compraDAO.alterar(compra);
    }

    public boolean excluir(int id) {
        return compraDAO.excluir(id);
    }

    public Compra pesquisar(int id) {
        return compraDAO.pesquisar(id);
    }
}
