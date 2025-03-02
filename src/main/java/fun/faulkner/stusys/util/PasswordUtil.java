package fun.faulkner.stusys.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    private static final int BCRYPT_ITERATIONS = 12;

    public static String hashPassword(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt(BCRYPT_ITERATIONS));
    }

    public static boolean verifyPassword(String plainText, String hash) {
        return BCrypt.checkpw(plainText, hash);
    }
}