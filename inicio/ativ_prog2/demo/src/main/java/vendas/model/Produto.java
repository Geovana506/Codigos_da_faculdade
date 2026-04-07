package vendas.model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private double qtdeEstoque;
    private Categoria categoria; 

    public Produto() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public double getQtdeEstoque() { return qtdeEstoque; }
    public void setQtdeEstoque(double qtdeEstoque) { this.qtdeEstoque = qtdeEstoque; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}