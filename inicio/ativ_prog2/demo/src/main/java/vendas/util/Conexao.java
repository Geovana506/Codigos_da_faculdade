package vendas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/atividade";
    private static final String USUARIO = "postgres";Claro! As mudanças foram focadas em alinhar a estrutura de pacotes com o diretório real do projeto. Aqui está o resumo:
    
    📋 Problemas Identificados
    O projeto tinha uma inconsistência de nomes:
    
    Os arquivos estavam localizados em: vendas/dao/, vendas/model/, etc.
    Mas os códigos declaravam: package br.com.vendas.dao;, package br.com.vendas.model;
    Além disso, havia referências a uma classe chamada ConexaoDB que não existia (o arquivo se chamava Conexao.java).
    
    🔧 Mudanças Realizadas
    1. Pacotes Corrigidos
    Mudei todas as declarações de pacote de:
    
    
    private static final String SENHA = "postgres"; 

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}