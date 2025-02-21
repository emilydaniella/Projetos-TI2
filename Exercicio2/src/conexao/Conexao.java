import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/teste";
    private static final String USUARIO = "postgres"; // Substitua pelo seu usuário do PostgreSQL
    private static final String SENHA = "daani840"; // Substitua pela senha do PostgreSQL

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver"); // Garante que o driver seja carregado
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.out.println(" Driver JDBC não encontrado: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println(" Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }
}
