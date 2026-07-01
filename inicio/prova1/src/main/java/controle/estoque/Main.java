package controle.estoque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controle.estoque.controller.CategoriaController;
import controle.estoque.controller.ClienteController;
import controle.estoque.controller.CompraController;
import controle.estoque.controller.FornecedorController;
import controle.estoque.controller.FornecedorProdutoController;
import controle.estoque.controller.ProdutoController;
import controle.estoque.controller.VendaController;
import controle.estoque.model.Categoria;
import controle.estoque.model.Cliente;
import controle.estoque.model.Compra;
import controle.estoque.model.CompraProduto;
import controle.estoque.model.Fornecedor;
import controle.estoque.model.FornecedorProduto;
import controle.estoque.model.Produto;
import controle.estoque.model.Venda;
import controle.estoque.model.VendaProduto;
import controle.estoque.view.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        CategoriaController categoriaController = new CategoriaController();
        ClienteController clienteController = new ClienteController();
        FornecedorController fornecedorController = new FornecedorController();
        FornecedorProdutoController fornecedorProdutoController = new FornecedorProdutoController();
        ProdutoController produtoController = new ProdutoController();
        CompraController compraController = new CompraController();
        VendaController vendaController = new VendaController();

        Categoria categoria = new Categoria(0, "Informatica");
        categoriaController.salvar(categoria);

        Cliente cliente = new Cliente(0, "Geovana Silva", "111.222.333-44", "MG-12.345.678",
                "Rua das Flores, 100", "(31) 99999-0000");
        clienteController.salvar(cliente);

        Fornecedor fornecedor = new Fornecedor(0, "Tech Distribuidora", "Tech Distribuidora LTDA",
                "12.345.678/0001-99");
        fornecedorController.salvar(fornecedor);

        Produto mouse = new Produto(0, "Mouse USB", 0, 0, 0, 0, categoria);
        Produto teclado = new Produto(0, "Teclado ABNT2", 0, 0, 0, 0, categoria);
        produtoController.salvar(mouse);
        produtoController.salvar(teclado);

        fornecedorProdutoController.salvar(new FornecedorProduto(0, fornecedor, mouse));
        fornecedorProdutoController.salvar(new FornecedorProduto(0, fornecedor, teclado));

        Compra compra = new Compra(0, fornecedor, LocalDate.now(), 0);
        List<CompraProduto> itensCompra = new ArrayList<>();
        itensCompra.add(new CompraProduto(0, mouse, 10, 25.00));
        itensCompra.add(new CompraProduto(0, teclado, 5, 80.00));
        compra.setItens(itensCompra);

        if (compraController.salvar(compra)) {
            menu.exibirMensagem("Compra salva e estoque atualizado para mais.");
        }

        Venda venda = new Venda(0, cliente, LocalDate.now(), 0);
        List<VendaProduto> itensVenda = new ArrayList<>();
        itensVenda.add(new VendaProduto(0, mouse, 2, 45.00));
        itensVenda.add(new VendaProduto(0, teclado, 1, 120.00));
        venda.setItens(itensVenda);

        if (vendaController.salvar(venda)) {
            menu.exibirMensagem("Venda salva e estoque atualizado para menos.");
        }

        Produto mouseAtualizado = produtoController.pesquisar(mouse.getId());
        if (mouseAtualizado != null) {
            menu.exibirMensagem("Estoque atual do mouse: " + mouseAtualizado.getQtdeEstoque());
            menu.exibirMensagem("Ultima compra do mouse: " + mouseAtualizado.getValorUltimaCompra());
            menu.exibirMensagem("Ultima venda do mouse: " + mouseAtualizado.getValorUltimaVenda());
            menu.exibirMensagem("Preco medio do mouse: " + mouseAtualizado.getPrecoMedio());
        }
    }
}
