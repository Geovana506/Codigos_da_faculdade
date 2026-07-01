package controle.estoque.controller;

import controle.estoque.dao.ClienteDAO;
import controle.estoque.model.Cliente;

public class ClienteController {
    ClienteDAO clienteDAO = new ClienteDAO();

    public boolean salvar(Cliente cliente) {
        return clienteDAO.salvar(cliente);
    }

    public boolean alterar(Cliente cliente) {
        return clienteDAO.alterar(cliente);
    }

    public boolean excluir(int id) {
        return clienteDAO.excluir(id);
    }

    public Cliente pesquisar(int id) {
        return clienteDAO.pesquisar(id);
    }
}
