package Services.Database;

import Models.DatabaseCredentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService {
    private static DatabaseService instance;
    private Connection connection;

    // Private constructor to prevent external instantiation
    private DatabaseService(DatabaseCredentials credentials) throws SQLException {
        // Establish the database connection
        this.connection = DriverManager.getConnection(credentials.getUrl(), credentials.getUser(), credentials.getPassword());
    }

    // Static method to return the single instance of the class
    public static DatabaseService getInstance() throws SQLException {
        // Create the instance if it hasn't been created yet
        if (instance == null) {
            DatabaseCredentials credentials = new DatabaseCredentials();
            instance = new DatabaseService(credentials);
        }
        return instance;
    }

    public PreparedStatement createStatement(String query, Object... parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
        return statement;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


