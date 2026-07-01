package controle.estoque.controller;

import controle.estoque.dao.ProdutoDAO;
import controle.estoque.model.Produto;

public class ProdutoController {
    ProdutoDAO produtoDAO = new ProdutoDAO();

    public boolean salvar(Produto produto) {
        return produtoDAO.salvar(produto);
    }

    public boolean alterar(Produto produto) {
        return produtoDAO.alterar(produto);
    }

    public boolean excluir(int id) {
        return produtoDAO.excluir(id);
    }

    public Produto pesquisar(int id) {
        return produtoDAO.pesquisar(id);
    }

    public boolean verificaEstoqueExistente(Produto produto, double quantidade) {
        return produtoDAO.existeEstoqueParaVenda(produto.getId(), quantidade);
    }
}
