package main;

import java.sql.Connection;

import util.Conexao;
import view.TelaPrincipal;

public class Main {

    public static void main(String[] args) {
        // Testar conexão com o banco
        Connection conn = Conexao.conectar();
        if (conn != null) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        } else {
            System.out.println("Falha na conexão com o banco de dados. Verifique as configurações.");
            return; // Não continua se não conectar
        }

        // Se conexão ok, abre a tela principal
        new TelaPrincipal().setVisible(true);
    }
}