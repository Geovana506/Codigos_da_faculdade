package controle.estoque.model;

public class Produto {
    private int id;
    private String nome;
    private double precoMedio;
    private double qtdeEstoque;
    private double valorUltimaCompra;
    private double valorUltimaVenda;
    private Categoria categoria;

    public Produto() {
    }

    public Produto(int id, String nome, double precoMedio, double qtdeEstoque, double valorUltimaCompra,
            double valorUltimaVenda, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.precoMedio = precoMedio;
        this.qtdeEstoque = qtdeEstoque;
        this.valorUltimaCompra = valorUltimaCompra;
        this.valorUltimaVenda = valorUltimaVenda;
        this.categoria = categoria;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(double precoMedio) {
        this.precoMedio = precoMedio;
    }

    public double getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(double qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public double getValorUltimaCompra() {
        return valorUltimaCompra;
    }

    public void setValorUltimaCompra(double valorUltimaCompra) {
        this.valorUltimaCompra = valorUltimaCompra;
    }

    public double getValorUltimaVenda() {
        return valorUltimaVenda;
    }

    public void setValorUltimaVenda(double valorUltimaVenda) {
        this.valorUltimaVenda = valorUltimaVenda;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
