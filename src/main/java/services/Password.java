package services;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Password {
    private static final int iterations = 65536;
    private static final int saltLen = 16;
    private static final int desiredKeyLen = 256;

    public static String getSaltedHash(String password) {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported");
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[saltLen];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt) + "$" + hashingPassword(password, salt);
    }

    private static String hashingPassword(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, desiredKeyLen);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean check(String password, String stored) {
        String[] saltAndPassword = stored.split("\\$");
        if (saltAndPassword.length != 2) {
            throw new IllegalStateException(
                    "The stored password haven't the form salt$hash");
        }
        String hashOfInput = hashingPassword(password, Base64.getDecoder().decode(saltAndPassword[0]));
        return hashOfInput.equals(saltAndPassword[1]);
    }
}