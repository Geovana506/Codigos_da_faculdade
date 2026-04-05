package controller;

import dao.ProdutorDAO;
import model.Produtor;

import java.util.List;

public class ProdutorController {

    private ProdutorDAO produtorDAO = new ProdutorDAO();

    public void listarProdutores() {
        List<Produtor> produtores = produtorDAO.listarProdutores();

        for (Produtor p : produtores) {
            System.out.println("ID: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Cidade: " + p.getCidade());
            System.out.println("------------------------");
        }
    }
}