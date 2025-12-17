package model;

import java.util.List;

public class Diario {

    private int id;
    private boolean status;
    private Aluno aluno;
    private Disciplina disciplina;
    private Turma turma;
    private Periodo periodo;

    public Diario() {}

    public Diario(int id, boolean status, Aluno aluno, Disciplina disciplina, Turma turma, Periodo periodo) {
        this.id = id;
        this.status = status;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.turma = turma;
        this.periodo = periodo;
    }

    public boolean validarNotas(List<Nota> notas) {
        double soma = 0;

        for (Nota n : notas) {
            soma += n.getNota();
        }

        double media = soma / notas.size();

        if (media >= 6.0) {
            status = true;
            System.out.println("Aluno aprovado - Média: " + media);
        } else {
            status = false;
            System.out.println("Aluno reprovado - Média: " + media);
        }
        return status;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }

    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }

    public Periodo getPeriodo() { return periodo; }
    public void setPeriodo(Periodo periodo) { this.periodo = periodo; }
}