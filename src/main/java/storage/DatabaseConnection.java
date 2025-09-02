package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost/task_db";
    private static final String USER_NAME = "postgres";
    private static final String DATABASE_PASS = "postgres";

    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, DATABASE_PASS);
        }

        return connection;
    }


}
