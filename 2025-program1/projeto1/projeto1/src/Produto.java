public class Produto {
    private String nome;
    private Double preco;
    private Integer qtde;
    private String marca;

    //Classe é qualquer representação de um objeto do mundo real -- Classe são processos
    //Atributos são as características do objeto
    //Métodos são as ações que o objeto pode realizar ou que podem ser realizadas sobre o objeto

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public Integer getQtde() {
        return qtde;
    }
    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
}