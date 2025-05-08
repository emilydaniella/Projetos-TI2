import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/teste";
    private static final String USUARIO = "postgres"; 
    private static final String SENHA = "dani123"; 

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver"); 
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
