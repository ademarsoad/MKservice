package com.br.mkservice.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Dell
 */
@ManagedBean
@RequestScoped
public class Conexao {
    private static final String url = "jdbc:postgresql://localhost:5432/MKservice";
    private final String password = "postgres";
    private final String user = "postgres";
    private Connection conection;

    public Conexao() {
    }
    
    public Connection ligaBanco() {
        try {
            Class.forName("org.postgresql.Driver");
            conection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexao bem sucedida");
        } catch (ClassNotFoundException e) {
            System.out.println("Conexão não pode ser completada: Erro " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Conexao interrompida: Erro " + e.getMessage());
        }

        return conection;
    }
    public void fechaConexao(){
        try {
            conection.commit();
            conection.close();
            conection = null;
        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }finally{
            conection = null;
        }
    }
    
}
