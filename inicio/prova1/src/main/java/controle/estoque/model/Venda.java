package controle.estoque.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controle.estoque.dao.VendaDAO;

public class Venda {
    private int id;
    private Cliente cliente;
    private LocalDate dataVenda;
    private double valorTotal;
    private List<VendaProduto> itens = new ArrayList<>();

    public Venda() {
    }

    public Venda(int id, Cliente cliente, LocalDate dataVenda, double valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
    }

    public boolean salvar() {
        return new VendaDAO().salvar(this);
    }

    public boolean alterar() {
        return new VendaDAO().alterar(this);
    }

    public boolean excluir() {
        return new VendaDAO().excluir(this.id);
    }

    public Venda pesquisar() {
        return new VendaDAO().pesquisar(this.id);
    }

    public void calcularValorTotal() {
        valorTotal = 0;
        for (VendaProduto item : itens) {
            valorTotal += item.calcularSubtotal();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<VendaProduto> getItens() {
        return itens;
    }

    public void setItens(List<VendaProduto> itens) {
        this.itens = itens;
    }
}
