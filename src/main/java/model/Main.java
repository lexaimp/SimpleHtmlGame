package model;

import services.AuthToken;
import services.db.JDBC;

public class Main {
    public static void main(String[] args) {
        JDBC jdbc = new JDBC();
        User user = jdbc.getUserByName("user");
        AuthToken authToken = new AuthToken();
        authToken.createToken(user);
        System.out.println(authToken.getToken());
    }
}
