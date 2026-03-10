package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/escola",
                "postgres",
                "postgres"
            );
        } catch (Exception e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            return null;
        }
    }
}