package fun.faulkner.stusys.dao;

import fun.faulkner.stusys.entity.Score;
import java.util.List;

public interface ScoreDao {
    List<Score> getScoreByStudentId(String studentId);
    boolean addScore(Score score);
    boolean updateScore(Score score);
    boolean deleteScore(int id);
    Score getScoreById(int id);
    List<Score> getAllScores();
}