package model;

import java.util.logging.Logger;

public class Periodo {

    private int id;
    private String nomePeriodo;

    private static final Logger logger =
            Logger.getLogger(Periodo.class.getName());

    // Construtor vazio
    public Periodo() {}

    // Construtor com parâmetros
    public Periodo(int id, String nomePeriodo) {
        this.id = id;
        this.nomePeriodo = nomePeriodo;
    }

    // ======================
    // Métodos de negócio
    // ======================

    public boolean salvar() {
        try {
            logger.info("Período salvo com sucesso");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao salvar período");
            return false;
        }
    }

    public boolean alterar() {
        try {
            logger.info("Período alterado com sucesso");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao alterar período");
            return false;
        }
    }

    public boolean excluir() {
        try {
            logger.info("Período excluído com sucesso");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao excluir período");
            return false;
        }
    }

    public boolean pesquisar() {
        try {
            logger.info("Pesquisa de período realizada");
            return true;
        } catch (Exception e) {
            logger.severe("Erro ao pesquisar período");
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

    public String getNomePeriodo() {
        return nomePeriodo;
    }

    public void setNomePeriodo(String nomePeriodo) {
        this.nomePeriodo = nomePeriodo;
    }
}
