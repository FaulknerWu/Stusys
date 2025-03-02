package fun.faulkner.stusys.dao;

import fun.faulkner.stusys.entity.Score;
import fun.faulkner.stusys.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreDaoImpl implements ScoreDao {
    private static final Logger LOGGER = Logger.getLogger(ScoreDaoImpl.class.getName());

    private Score mapResultSetToScore(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String studentId = resultSet.getString("student_id");
        String courseId = resultSet.getString("course_id");
        int scoreValue = resultSet.getInt("score");

        return new Score(id, courseId, studentId, scoreValue);
    }

    @Override
    public List<Score> getScoreByStudentId(String studentId) {
        List<Score> scores = new ArrayList<>();
        String sql = "SELECT * FROM scores WHERE student_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Score score = mapResultSetToScore(resultSet);
                    scores.add(score);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting scores by student ID", e);
        }

        return scores;
    }
    @Override
    public boolean addScore(Score score) {
        String sql = "INSERT INTO scores (course_id, student_id, score) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, score.getCourseId());
            pstmt.setString(2, score.getStudentId());
            pstmt.setDouble(3, score.getScore());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows != 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding score", e);
            return false;
        }
    }

    @Override
    public boolean updateScore(Score score) {
        String sql = "UPDATE scores SET course_id = ?, student_id = ?, score = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, score.getCourseId());
            pstmt.setString(2, score.getStudentId());
            pstmt.setDouble(3, score.getScore());
            pstmt.setInt(4, score.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating score", e);
            return false;
        }
    }

    @Override
    public boolean deleteScore(int id) {
        String sql = "DELETE FROM scores WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting score", e);
            return false;
        }
    }

    @Override
    public Score getScoreById(int id) {
        String sql = "SELECT * FROM scores WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? mapResultSetToScore(rs) : null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting score by ID", e);
            return null;
        }
    }

    @Override
    public List<Score> getAllScores() {
        List<Score> scores = new ArrayList<>();
        String sql = "SELECT * FROM scores";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                scores.add(mapResultSetToScore(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting all scores", e);
        }
        return scores;
    }
}