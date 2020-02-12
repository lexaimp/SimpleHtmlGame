package services.db;

import model.User;

import java.sql.*;

public class JDBC {
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
                    "VALUES (" + getUserByName(name).getId() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getUserByName(String name) {
        try (Statement statement = getStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT u.id, u.name, u.password, u.date_created, at.damage, at.life, at.rating " +
                            "FROM users u " +
                            "JOIN attributes at ON at.user_id = u.id " +
                            "WHERE name = '" + name + "'");
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setDamage(resultSet.getInt("damage"));
                user.setLife(resultSet.getInt("life"));
                user.setRating(resultSet.getInt("rating"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getAttributes() {
    }

    // TODO: 09.02.2020  hashing
    public void createToken(User user) {
        try (Statement statement = getStatement()) {
            statement.execute("INSERT INTO session" +
                    "(active, user_id)" +
                    "VALUES (1, " + user.getId() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}