package controle.estoque.model;

public class CompraProduto {
    private int id;
    private Produto produto;
    private double quantidade;
    private double valorUnitario;

    public CompraProduto() {
    }

    public CompraProduto(int id, Produto produto, double quantidade, double valorUnitario) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public double calcularSubtotal() {
        return quantidade * valorUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
