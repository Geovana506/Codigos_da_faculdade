package controle.estoque.model;

import controle.estoque.dao.CategoriaDAO;

public class Categoria {
    private int id;
    private String nome;

    public Categoria() {
    }

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public boolean salvar() {
        return new CategoriaDAO().salvar(this);
    }

    public boolean alterar() {
        return new CategoriaDAO().alterar(this);
    }

    public boolean excluir() {
        return new CategoriaDAO().excluir(this.id);
    }

    public Categoria pesquisar() {
        return new CategoriaDAO().pesquisar(this.id);
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
}
