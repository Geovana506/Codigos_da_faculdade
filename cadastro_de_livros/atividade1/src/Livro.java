public class Livro {
    private Integer id;
    private String nome;
    private String resenha;
    private Integer edicao;
    private Integer ano_publicacao;
    private Integer n_paginas;
    private Autor autor;
    private Editora editora;
    private TipoCapa tipoCapa;

    public Livro() {
    }
    public Livro(Integer id, String nome, String resenha, Integer edicao, Integer ano_publicacao, Integer n_paginas, Autor autor, Editora editora, TipoCapa tipoCapa) {
        this.id = id;
        this.nome = nome;
        this.resenha = resenha;
        this.edicao = edicao;
        this.ano_publicacao = ano_publicacao;
        this.n_paginas = n_paginas;
        this.autor = autor;
        this.editora = editora;
        this.tipoCapa = tipoCapa;
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
    public String getResenha() {
        return resenha;
    }
    public void setResenha(String resenha) {
        this.resenha = resenha;
    }
    public Integer getEdicao() {
        return edicao;
    }
    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }
    public Integer getAno_publicacao() {
        return ano_publicacao;
    }
    public void setAno_publicacao(Integer ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }
    public Integer getN_paginas() {
        return n_paginas;
    }
    public void setN_paginas(Integer n_paginas) {
        this.n_paginas = n_paginas;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public Editora getEditora() {
        return editora;
    }
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    public TipoCapa getTipoCapa() {
        return tipoCapa;
    }
    public void setTipoCapa(TipoCapa tipoCapa) {
        this.tipoCapa = tipoCapa;
    }

    
    public void salvar () {
        System.out.println("Livro salvo com sucesso!");
    }
    public void alterar () {
        System.out.println("Livro alterado com sucesso!");
    }
    public void excluir () {
        System.out.println("Livro excluído com sucesso!");
    }
    public void pesquisar () {
        System.out.println("Livro pesquisado com sucesso!");
    }
}
