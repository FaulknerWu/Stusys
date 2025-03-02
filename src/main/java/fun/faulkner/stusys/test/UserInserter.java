package fun.faulkner.stusys.test;

import fun.faulkner.stusys.util.DBUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInserter {
    public static void main(String[] args) {
        insertUser("admin", "admin123", "admin");
        insertUser("testuser", "user123", "user");
    }

    private static void insertUser(String username, String password, String role) {
        String hashedPw = BCrypt.hashpw(password, BCrypt.gensalt());

        String sql = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, hashedPw);
            pstmt.setString(3, role);

            int rows = pstmt.executeUpdate();
            System.out.println("插入用户 " + username + " 成功，影响行数: " + rows);

        } catch (SQLException e) {
            System.err.println("插入用户失败: " + e.getMessage());
            if (e.getErrorCode() == 1062) {
                System.err.println("错误原因: 用户名已存在");
            }
        }
    }
}