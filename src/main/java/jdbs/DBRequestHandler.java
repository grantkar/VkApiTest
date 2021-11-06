package jdbs;

import api.utils.UtilsMethod;
import java.sql.*;

public class DBRequestHandler {
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/tokenAPI";

    private static final String USER_NAME = UtilsMethod.getValue("USER_NAME");
    private static final String USER_PASSWORD = UtilsMethod.getValue("USER_PASSWORD");

    private static Connection connection;
    private static Statement statement;

    /**
     * Метод устаналивает соедение с БД.
     * @return - объект соединения с БД
     */
    public static Connection connect() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USER_NAME, USER_PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Метод разрывает соединение с БД.
     */
    public void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getToken() {
        String dbQuery = "Select hash From tokens Where name = 'VKAPI'";
        String hash = "";
        try {
            ResultSet resultSet = statement.executeQuery(dbQuery);
            while (resultSet.next()){
                hash = resultSet.getString("hash");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
