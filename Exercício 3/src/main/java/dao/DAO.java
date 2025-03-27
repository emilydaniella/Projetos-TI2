// Desenvolvido por Daniella Silva - Ciência da Computação - PUC Minas - 2025

package dao;

import java.sql.*;

public class DAO {
    protected Connection conexao;
    
    public DAO() {
        conexao = null;
    }
    
    public boolean abrirConexao() {
        String nomeDriver = "org.postgresql.Driver";                    
        String nomeServidor = "localhost";
        String nomeBancoDados = "school";
        int porta = 5432;
        String url = "jdbc:postgresql://" + nomeServidor + ":" + porta + "/" + nomeBancoDados;
        String nomeUsuario = "emilydaniella";
        String senha = "dani123";
        boolean status = false;

        try {
            Class.forName(nomeDriver);
            conexao = DriverManager.getConnection(url, nomeUsuario, senha);
            status = (conexao == null);
            System.out.println("Conexão estabelecida com PostgreSQL!");
        } catch (ClassNotFoundException e) { 
            System.err.println("Conexão NÃO estabelecida com PostgreSQL -- Driver não encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conexão NÃO estabelecida com PostgreSQL -- " + e.getMessage());
        }

        return status;
    }
    
    public boolean fecharConexao() {
        boolean status = false;
        
        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }
}