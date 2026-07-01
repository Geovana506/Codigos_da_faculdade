package controle.estoque.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controle.estoque.dao.CompraDAO;

public class Compra {
    private int id;
    private Fornecedor fornecedor;
    private LocalDate dataCompra;
    private double valorTotal;
    private List<CompraProduto> itens = new ArrayList<>();

    public Compra() {
    }

    public Compra(int id, Fornecedor fornecedor, LocalDate dataCompra, double valorTotal) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
    }

    public boolean salvar() {
        return new CompraDAO().salvar(this);
    }

    public boolean alterar() {
        return new CompraDAO().alterar(this);
    }

    public boolean excluir() {
        return new CompraDAO().excluir(this.id);
    }

    public Compra pesquisar() {
        return new CompraDAO().pesquisar(this.id);
    }

    public void calcularValorTotal() {
        valorTotal = 0;
        for (CompraProduto item : itens) {
            valorTotal += item.calcularSubtotal();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<CompraProduto> getItens() {
        return itens;
    }

    public void setItens(List<CompraProduto> itens) {
        this.itens = itens;
    }
}
