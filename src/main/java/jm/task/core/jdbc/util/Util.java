package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String name = "root";
    private static final String pass = "12345";

        public static Connection createConnection() {
            Connection connection = null;

            {
                try {
                    connection = DriverManager.getConnection(url, name, pass);
                    connection.setAutoCommit(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return connection;
        }

    }


