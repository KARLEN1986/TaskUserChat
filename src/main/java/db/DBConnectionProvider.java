package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {

    private static DBConnectionProvider provider;

    private Connection connection;

    private String dbUrl;
    private String dbDriver;
    private String dbUsername;
    private String dbPassword;

    private DBConnectionProvider(){
        try {
            loadConfigProperties();
            Class.forName(dbDriver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to " +
                    "initialize DB Connection Provider", e);
        }
    }

    private void loadConfigProperties() throws IOException {
        InputStream inputStream = DBConnectionProvider.class.getClassLoader().getResourceAsStream("config.properties");
        Properties dbProperties = new Properties();
        dbProperties.load(inputStream);

        dbDriver = dbProperties.getProperty("db.driver");
        dbUrl = dbProperties.getProperty("db.url");
        dbUsername = dbProperties.getProperty("db.username");
        dbPassword = dbProperties.getProperty("db.password");
    }
    public static DBConnectionProvider getInstance(){
        if (provider==null){
            synchronized (DBConnectionProvider.class){
                provider = new DBConnectionProvider();
            }
        }
        return provider;
    }

    public synchronized Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }
}
