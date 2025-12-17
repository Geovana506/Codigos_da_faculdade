package model;

import java.util.logging.Logger;

public class Turma {

    private int id;
    private String nomeTurma;

    private static final Logger logger =
            Logger.getLogger(Turma.class.getName());

    // Construtor vazio
    public Turma() {}

    // Construtor com parâmetros
    public Turma(int id, String nomeTurma) {
        this.id = id;
        this.nomeTurma = nomeTurma;
    }


    public boolean salvar() {
        try {
            logger.info("Iniciando salvamento da turma");
            // Aqui chamará a TurmaDAO futuramente
            logger.info("Turma salva com sucesso");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao salvar turma");
            return false;
        }
    }

    public boolean alterar() {
        try {
            logger.info("Iniciando alteração da turma");
            logger.info("Turma alterada com sucesso");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao alterar turma");
            return false;
        }
    }

    public boolean excluir() {
        try {
            logger.info("Iniciando exclusão da turma");
            logger.info("Turma excluída com sucesso");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao excluir turma");
            return false;
        }
    }

    public boolean pesquisar() {
        try {
            logger.info("Pesquisando turma");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao pesquisar turma");
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }
}
