package principal;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
    private MysqlDataSource dataSource;

    
    public ConexionBD() {
        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("src/main/resources/db.properties");
            prop.load(fis);

            
            dataSource = new MysqlDataSource();
            dataSource.setUrl(prop.getProperty("url"));
            dataSource.setUser(prop.getProperty("usuario"));
            dataSource.setPassword(prop.getProperty("password"));
        } catch (IOException e) {
            System.err.println("Error al cargar db.properties: " + e.getMessage());
        }
    }

    
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
