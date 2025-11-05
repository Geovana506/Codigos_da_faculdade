import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaTarefas {

    private static List<ListaTarefas> bancoTarefas = new ArrayList<>();

    private int id;
    private Date data_tarefa;
    private String descricao_tarefa;
    private String observacao;

    private Responsavel responsavel;
    private List<Prioridade> prioridades;

    public ListaTarefas() {
        prioridades = new ArrayList<>();
    }

    public ListaTarefas(int id, Date data_tarefa, String descricao_tarefa, String observacao, Responsavel responsavel) {
        this.id = id;
        this.data_tarefa = data_tarefa;
        this.descricao_tarefa = descricao_tarefa;
        this.observacao = observacao;
        this.responsavel = responsavel;
        this.prioridades = new ArrayList<>();
    }

    public boolean salvar() {
        return bancoTarefas.add(this);
    }

    public boolean alterar() {
        for (ListaTarefas t : bancoTarefas) {
            if (t.getId() == this.id) {
                t.setData_tarefa(this.data_tarefa);
                t.setDescricao_tarefa(this.descricao_tarefa);
                t.setObservacao(this.observacao);
                t.setResponsavel(this.responsavel);
                t.prioridades = this.prioridades;
                return true;
            }
        }
        return false;
    }

    public boolean excluir() {
        return bancoTarefas.removeIf(t -> t.getId() == this.id);
    }

    public boolean pesquisar() {
        for (ListaTarefas t : bancoTarefas) {
            if (t.getId() == this.id) {
                System.out.println("Tarefa encontrada: " + t.getDescricao_tarefa());
                return true;
            }
        }
        return false;
    }

    public boolean buscarResponsavel() {
        if (responsavel == null) return false;
        System.out.println("Responsável: " + responsavel.getNome());
        return true;
    }

    public boolean buscarPrioridade() {
        if (prioridades.isEmpty()) return false;
        System.out.println("Quantidade de prioridades: " + prioridades.size());
        return true;
    }

    public void adicionarPrioridade(Prioridade p) {
        prioridades.add(p);
    }

    // Getters e Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getData_tarefa() { return data_tarefa; }
    public void setData_tarefa(Date data_tarefa) { this.data_tarefa = data_tarefa; }

    public String getDescricao_tarefa() { return descricao_tarefa; }
    public void setDescricao_tarefa(String descricao_tarefa) { this.descricao_tarefa = descricao_tarefa; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public Responsavel getResponsavel() { return responsavel; }
    public void setResponsavel(Responsavel responsavel) { this.responsavel = responsavel; }

    public List<Prioridade> getPrioridades() { return prioridades; }
}
