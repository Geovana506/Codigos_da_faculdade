package model;

import java.util.logging.Logger;

public class Nota {

    private int id;
    private double nota;
    private String situacao;

    private static final Logger logger =
            Logger.getLogger(Nota.class.getName());

    public Nota() {}

    public Nota(int id, double nota) {
        this.id = id;
        this.nota = nota;
        calcularSituacao();
    }

    public void calcularSituacao() {
        if (nota < 0 || nota > 10) {
            logger.severe("Nota fora do intervalo permitido");
            throw new IllegalArgumentException("Nota deve ser entre 0 e 10");
        }

        if (nota >= 6.0) {
            situacao = "Aprovado";
        } else {
            situacao = "Reprovado";
        }

        logger.info("Situação calculada com sucesso");
    }

    // Getters e Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getNota() { return nota; }
    public void setNota(double nota) { 
        this.nota = nota; 
        calcularSituacao();
    }

    public String getSituacao() { return situacao; }
}