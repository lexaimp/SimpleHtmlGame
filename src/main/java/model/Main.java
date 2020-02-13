package model;

import services.Password;
import services.db.JDBC;

public class Main {
    public static void main(String[] args) {
        JDBC jdbc = new JDBC();
        User user = jdbc.getUserByName("user");
        String hashingPassword = Password.getSaltedHash("asd123");
        System.out.println(hashingPassword);
        System.out.println(Password.check("asd1234", hashingPassword));
    }
}
