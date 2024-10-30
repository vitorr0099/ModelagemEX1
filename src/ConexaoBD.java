import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static ConexaoBD instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/sua_database";
    private String username = "usuario";
    private String password = "senha";

    private ConexaoBD() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao conectar ao banco de dados.");
        }
    }

    public static ConexaoBD getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConexaoBD();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConexaoBD();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
