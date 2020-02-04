package model;

import services.db.JDBCConnector;

public class Main {
    public static void main(String[] args) {
        JDBCConnector jdbcConnector = new JDBCConnector();
        User user = jdbcConnector.getUser("user");
        System.out.println(user.getPassword());
    }
}
