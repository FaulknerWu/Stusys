package fun.faulkner.stusys.service;

import fun.faulkner.stusys.dao.ScoreDaoImpl;
import fun.faulkner.stusys.entity.Score;
import java.util.List;

public class ScoreServiceImpl implements ScoreService {
    private final ScoreDaoImpl scoreDao;

    public ScoreServiceImpl(ScoreDaoImpl scoreDao) {
        this.scoreDao = scoreDao;
    }

    @Override
    public List<Score> getScoreByStudentId(String studentId) {
        return scoreDao.getScoreByStudentId(studentId);
    }

    @Override
    public boolean addScore(Score score) {
        return scoreDao.addScore(score);
    }

    @Override
    public boolean updateScore(Score score) {
        return scoreDao.updateScore(score);
    }

    @Override
    public boolean deleteScore(int id) {
        return scoreDao.deleteScore(id);
    }

    @Override
    public Score getScoreById(int id) {
        return scoreDao.getScoreById(id);
    }

    @Override
    public List<Score> getAllScores() {
        return scoreDao.getAllScores();
    }
}