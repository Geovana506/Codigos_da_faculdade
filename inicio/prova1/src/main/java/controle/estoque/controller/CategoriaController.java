package controle.estoque.controller;

import controle.estoque.dao.CategoriaDAO;
import controle.estoque.model.Categoria;
 
public class CategoriaController {
    CategoriaDAO categoriaDAO = new CategoriaDAO();

    public boolean salvar(Categoria categoria) {
        return categoriaDAO.salvar(categoria);
    }

    public boolean alterar(Categoria categoria) {
        return categoriaDAO.alterar(categoria);
    }

    public boolean excluir(int id) {
        return categoriaDAO.excluir(id);
    }

    public Categoria pesquisar(int id) {
        return categoriaDAO.pesquisar(id);
    }
}
