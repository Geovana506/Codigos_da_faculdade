package vendas.controller;

import java.util.Date;

import vendas.dao.ClienteDAO;
import vendas.dao.ProdutoDAO;
import vendas.dao.VendaDAO;
import vendas.model.Cliente;
import vendas.model.Produto;
import vendas.model.Venda;

public class Vendascontroller {

    private VendaDAO vendaDAO = new VendaDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO(); 

    public boolean processarVenda(int idCliente, int idProduto, double quantidadeComprada) {
        
        System.out.println("\n--- Iniciando processamento da venda ---");

        Cliente cliente = clienteDAO.buscarPorId(idCliente);
        Produto produto = produtoDAO.buscarPorId(idProduto);

        if (cliente == null) {
            System.out.println("ERRO: Cliente com ID " + idCliente + " não existe no banco.");
            return false;
        }
        if (produto == null) {
            System.out.println("ERRO: Produto com ID " + idProduto + " não existe no banco.");
            return false;
        }


        // Estoque
        if (produto.getQtdeEstoque() < 1 || quantidadeComprada > produto.getQtdeEstoque()) {
            System.out.println("ERRO: Produto '" + produto.getNome() + "' sem estoque suficiente. Disponível: " + produto.getQtdeEstoque());
            return false;
        }

        
        if (cliente.getNome().equalsIgnoreCase("Flávio Vilela") || cliente.getNome().equalsIgnoreCase("Flavio Vilela")) {
            int vendasAnteriores = vendaDAO.contarVendasPorCliente(cliente.getId());
            if (vendasAnteriores >= 3) {
                System.out.println("ALERTA: O cliente Flávio Vilela atingiu a quantidade máxima de vendas (3). Operação não autorizada.");
                return false; 
            }
        }

        // Atualizar Estoque
        double estoqueAtualizado = produto.getQtdeEstoque() - quantidadeComprada;
        produto.setQtdeEstoque(estoqueAtualizado); 
        
        if (!produtoDAO.alterar(produto)) {
            System.out.println("ERRO: Falha ao atualizar o estoque no banco.");
            return false; 
        }

        // Salvar Venda
        Venda venda = new Venda();
        venda.setDataVenda(new Date());
        venda.setValorTotal(produto.getPreco() * quantidadeComprada); 
        venda.setCliente(cliente); 
        
        if (vendaDAO.salvar(venda, produto, quantidadeComprada)) {
            System.out.println("SUCESSO: Venda finalizada! Novo estoque de '" + produto.getNome() + "': " + produto.getQtdeEstoque());
            return true;
        } else {
            return false;
        }
    }
}