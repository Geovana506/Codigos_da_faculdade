public class Main {
    public static void main(String[] args) throws Exception {
        Editora editora1 = new Editora(1, "Romero");
        Autor autor1 = new Autor(1, "Geovana", "Jataí");
        TipoCapa tipoCapa1 = new TipoCapa(1, "Dura");
        Livro livro1 = new Livro(1, "Secomp", "Livro de louco", 1, 2023, 300, autor1, editora1, tipoCapa1);

        System.out.println("Detalhes do Livro:");
        System.out.println("Livro: " + livro1.getNome());
        System.out.println("Autor: " + livro1.getAutor().getNome());    
        System.out.println("Editora: " + livro1.getEditora().getNome_editora());
        System.out.println("Tipo de Capa: " + livro1.getTipoCapa().getDescricao());
        System.out.println("---------------------------");

        System.out.println("---------------------------");
        livro1.salvar();
        livro1.alterar();   
        livro1.excluir();
        livro1.pesquisar();
        System.out.println("---------------------------");

        System.out.println("---------------------------");
        autor1.salvar();
        autor1.alterar();
        autor1.excluir();
        autor1.pesquisar();
        System.out.println("---------------------------");

        System.out.println("---------------------------");
        editora1.salvar();
        editora1.alterar();
        editora1.excluir();
        editora1.pesquisar();
        System.out.println("---------------------------");

        System.out.println("---------------------------");
        tipoCapa1.salvar();
        tipoCapa1.alterar();
        tipoCapa1.excluir();
        tipoCapa1.pesquisar();
        System.out.println("---------------------------");
    }
}
