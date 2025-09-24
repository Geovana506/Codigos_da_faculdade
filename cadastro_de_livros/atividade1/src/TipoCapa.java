public class TipoCapa {
    private Integer id;
    private String descricao;  

    public TipoCapa() {
    }
    public TipoCapa(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }

    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void salvar () {
        System.out.println("Tipo de capa salvo com sucesso!");
    }
    public void alterar () {
        System.out.println("Tipo de capa alterado com sucesso!");
    }
    public void excluir () {
        System.out.println("Tipo de capa excluído com sucesso!");
    }
    public void pesquisar () {
        System.out.println("Tipo de capa pesquisado com sucesso!");
    }
}
