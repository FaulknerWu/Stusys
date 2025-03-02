package fun.faulkner.stusys.service;

import fun.faulkner.stusys.entity.Score;
import java.util.List;

public interface ScoreService {
    List<Score> getScoreByStudentId(String studentId);
    boolean addScore(Score score);
    boolean updateScore(Score score);
    boolean deleteScore(int id);
    Score getScoreById(int id);
    List<Score> getAllScores();
}