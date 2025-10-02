public class Categoria {
    private String descricao;

    public Categoria() {
    }
    public Categoria(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
        public void salvar () {
        System.out.println("Categoria salva com sucesso!");
    }
    public void alterar () {
        System.out.println("Categoria alterada com sucesso!");
    }   
    public void excluir () {
        System.out.println("Categoria excluída com sucesso!");
    }
    public void pesquisar () {
        System.out.println("Categoria pesquisada com sucesso!");
    }
}
