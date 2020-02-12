package services;

import model.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class AuthToken {
    private String token;

    public String getToken() {
        return token;
    }

    public void createToken(User user) {
        if (user == null) {
            return;
        }
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        String token = user.getId() +
                user.getName() +
                user.getPassword() +
                user.getDate() +
                new java.util.Date().getTime();
        KeySpec spec = new PBEKeySpec(token.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            for (byte b : salt) {
                System.out.println(b);
            }
            this.token = new String(hash, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}
