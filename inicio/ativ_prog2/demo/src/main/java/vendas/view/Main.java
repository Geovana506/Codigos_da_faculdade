package vendas.view;

import java.util.Scanner;

import vendas.controller.Vendascontroller;
import vendas.dao.ClienteDAO;
import vendas.dao.FornecedorDAO;
import vendas.dao.ProdutoDAO;
import vendas.model.Categoria;
import vendas.model.Cliente;
import vendas.model.Fornecedor;
import vendas.model.Produto;

public class Main {
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in, "UTF-8");
        int opcao = -1;

        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Vendascontroller vendascontroller = new Vendascontroller();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();

         System.out.println("\nSeja bem-vindo ao Sistema de Gestão de Vendas da Secomp!\n");

        while (opcao != 0) {

            System.out.println("     VENDAS         ");
            System.out.println("1 - Cadastrar Cliente      2 - Pesquisar Cliente");
            System.out.println("3 - Alterar Cliente        4 - Excluir Cliente");
            System.out.println("5 - Realizar Venda         6 - Listar Clientes");
            System.out.println("----------------------------------");
            System.out.println("7 - Cadastrar Produto      8 - Alterar Estoque");
            System.out.println("9 - Listar Produtos       10 - Cadastrar Fornecedor");
            System.out.println("11 - Listar Fornecedores     0 - Sair");
            System.out.print("Escolha: ");
            
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n-- CADASTRAR CLIENTE --");
                    Cliente novoCliente = new Cliente();
                    System.out.print("Digite o nome: ");
                    novoCliente.setNome(teclado.nextLine());
                    System.out.print("Digite o CPF: ");
                    novoCliente.setCpf(teclado.nextLine());
                    System.out.print("Digite o RG: ");
                    novoCliente.setRg(teclado.nextLine());
                    System.out.print("Digite o Endereço: ");
                    novoCliente.setEndereco(teclado.nextLine());
                    System.out.print("Digite o Telefone: ");
                    novoCliente.setTelefone(teclado.nextLine());
                    clienteDAO.salvar(novoCliente);
                    break;

                case 2:
                    System.out.println("\n-- PESQUISAR CLIENTE --");
                    System.out.print("Digite o ID do cliente: ");
                    clienteDAO.pesquisar(teclado.nextInt());
                    break;

                case 3:
                    System.out.println("\n-- ALTERAR CLIENTE --");
                    System.out.print("Digite o ID do cliente que deseja alterar: ");
                    int idAlterar = teclado.nextInt();
                    teclado.nextLine(); 
                    
                    Cliente clienteParaAlterar = new Cliente();
                    clienteParaAlterar.setId(idAlterar);
                    System.out.print("Digite o NOVO nome: ");
                    clienteParaAlterar.setNome(teclado.nextLine());
                    System.out.print("Digite o NOVO CPF: ");
                    clienteParaAlterar.setCpf(teclado.nextLine());
                    System.out.print("Digite o NOVO RG: ");
                    clienteParaAlterar.setRg(teclado.nextLine());
                    System.out.print("Digite o NOVO Endereço: ");
                    clienteParaAlterar.setEndereco(teclado.nextLine());
                    System.out.print("Digite o NOVO Telefone: ");
                    clienteParaAlterar.setTelefone(teclado.nextLine());
                    clienteDAO.alterar(clienteParaAlterar);
                    break;

                case 4:
                    System.out.println("\n-- EXCLUIR CLIENTE --");
                    System.out.print("Digite o ID do cliente que deseja EXCLUIR: ");
                    int idExcluir = teclado.nextInt();
                    System.out.println("Tem certeza? (1 para SIM / 2 para NÃO)");
                    if (teclado.nextInt() == 1) {
                        clienteDAO.excluir(idExcluir);
                    } else {
                        System.out.println("Exclusão cancelada.");
                    }
                    break;

                case 5:
                    System.out.println("\n-- REALIZAR VENDA --");
                    System.out.print("Digite o ID do Cliente: ");
                    int idC = teclado.nextInt();
                    System.out.print("Digite o ID do Produto vendido: ");
                    int idP = teclado.nextInt();
                    System.out.print("Digite a quantidade comprada: ");
                    double qtd = teclado.nextDouble();
                    vendascontroller.processarVenda(idC, idP, qtd);
                    break;

                case 6:
                    clienteDAO.listarTodos();
                    break;

                case 7:
                    System.out.println("\n-- CADASTRAR PRODUTO --");
                    Produto p = new Produto();
                    System.out.print("Nome do Produto: ");
                    p.setNome(teclado.nextLine());
                    System.out.print("Preço (ex: 1500): ");
                    p.setPreco(teclado.nextDouble());
                    System.out.print("Estoque Inicial: ");
                    p.setQtdeEstoque(teclado.nextDouble());
                    System.out.print("ID da Categoria (ex: 1): ");
                    int idCat = teclado.nextInt();
                    
                    p.setCategoria(new Categoria(idCat, "")); 
                    produtoDAO.salvar(p);
                    break;

                case 8:
                    System.out.println("\n-- ALTERAR ESTOQUE --");
                    System.out.print("ID do Produto: ");
                    int idProdEstoque = teclado.nextInt();
                    System.out.print("Nova Quantidade Total em Estoque: ");
                    double novaQtd = teclado.nextDouble();
                    
                    if (produtoDAO.atualizarEstoque(idProdEstoque, novaQtd)) {
                        System.out.println("SUCESSO: Estoque atualizado!");
                    } else {
                        System.out.println("ERRO: Produto não encontrado.");
                    }
                    break;

                case 9:
                    produtoDAO.listarTodos();
                    break;

                case 10:
                    System.out.println("\n-- CADASTRAR FORNECEDOR --");
                    Fornecedor f = new Fornecedor();
                    
                    System.out.print("Razão Social (Nome Jurídico): ");
                    f.setRazaoSocial(teclado.nextLine());
                    
                    System.out.print("CNPJ: ");
                    f.setCnpj(teclado.nextLine());
                    
                    System.out.print("Nome Fantasia (Nome Comercial): ");
                    f.setNomeFantasia(teclado.nextLine());
                    
                    fornecedorDAO.salvar(f);
                    break;

                    
                case 11:
                    fornecedorDAO.listarTodos();
                    break;

                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        teclado.close(); 
    }
}