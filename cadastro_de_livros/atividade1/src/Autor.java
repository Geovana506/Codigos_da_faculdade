public class Autor {
    private Integer id;
    private String nome;
    private String cidade;

    public Autor() {
    }
    public Autor(Integer id, String nome, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    
    public void salvar () {
        System.out.println("Autor salvo com sucesso!");
    }
    public void alterar () {
        System.out.println("Autor alterado com sucesso!");
    }
    public void excluir () {
        System.out.println("Autor excluído com sucesso!");
    }
    public void pesquisar () {
        System.out.println("Autor pesquisado com sucesso!");
    }
}
