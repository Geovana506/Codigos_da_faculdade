package controle.estoque.model;

import controle.estoque.dao.FornecedorProdutoDAO;

public class FornecedorProduto {
    private int id;
    private Fornecedor fornecedor;
    private Produto produto;

    public FornecedorProduto() {
    }

    public FornecedorProduto(int id, Fornecedor fornecedor, Produto produto) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.produto = produto;
    }

    public boolean salvar() {
        return new FornecedorProdutoDAO().salvar(this);
    }

    public boolean alterar() {
        return new FornecedorProdutoDAO().alterar(this);
    }

    public boolean excluir() {
        return new FornecedorProdutoDAO().excluir(this.id);
    }

    public FornecedorProduto pesquisar() {
        return new FornecedorProdutoDAO().pesquisar(this.id);
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
