public class App {
    public static void main(String[] args) {

        Responsavel r = new Responsavel(1, "Maria");
        r.salvar();

        Prioridade p1 = new Prioridade(1, "Alta");
        Prioridade p2 = new Prioridade(2, "Média");
        p1.salvar(); 
        p2.salvar();

        ListaTarefas tarefa = new ListaTarefas(
            1, 
            new java.util.Date(), 
            "Estudar UML", 
            "Revisar diagramas", 
            r
        );

        tarefa.adicionarPrioridade(p1);
        tarefa.salvar();

        tarefa.pesquisar();
        tarefa.buscarResponsavel();
        tarefa.buscarPrioridade();
    }
}
