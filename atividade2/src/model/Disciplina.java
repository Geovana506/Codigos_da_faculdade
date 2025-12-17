package model;

import java.util.logging.Logger;

public class Disciplina {

    private int id;
    private String nomeDisciplina;

    private static final Logger logger =
            Logger.getLogger(Disciplina.class.getName());

    // Construtor vazio
    public Disciplina() {}

    // Construtor com parâmetros
    public Disciplina(int id, String nomeDisciplina) {
        this.id = id;
        this.nomeDisciplina = nomeDisciplina;
    }

    // ======================
    // Métodos de negócio
    // ======================

    public boolean salvar() {
        try {
            logger.info("Iniciando salvamento da disciplina");
            // chamada da DisciplinaDAO futuramente
            logger.info("Disciplina salva com sucesso");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao salvar disciplina");
            return false;
        }
    }

    public boolean alterar() {
        try {
            logger.info("Iniciando alteração da disciplina");
            logger.info("Disciplina alterada com sucesso");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao alterar disciplina");
            return false;
        }
    }

    public boolean excluir() {
        try {
            logger.info("Iniciando exclusão da disciplina");
            logger.info("Disciplina excluída com sucesso");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao excluir disciplina");
            return false;
        }
    }

    public boolean pesquisar() {
        try {
            logger.info("Pesquisando disciplina");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao pesquisar disciplina");
            return false;
        }
    }

    // ======================
    // Getters e Setters
    // ======================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }
}
