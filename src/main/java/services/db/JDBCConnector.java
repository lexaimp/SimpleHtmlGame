package services.db;

import model.User;

import java.sql.*;

public class JDBCConnector {
    private static final String url = "jdbc:mysql://localhost:3306/testbd?useSSL=false";
    private static final String user = "user";
    private static final String password = "user";

    private static Statement getStatement() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection.createStatement();
    }

    public void createUser(String name, String password, Date date) {
        try (Statement statement = getStatement()) {
            statement.execute("INSERT INTO users" +
                    "(name, password, date_created)" +
                    "VALUES ('" + name + "','" + password + "','" + date + "')");
            statement.execute("INSERT INTO attributes" +
                    "(user_id)" +
                    "VALUES (" + getUser(name).getId() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String name) {
        try (Statement statement = getStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM users " +
                    "WHERE name = '" + name + "'");
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getAttributes() {

    }
}