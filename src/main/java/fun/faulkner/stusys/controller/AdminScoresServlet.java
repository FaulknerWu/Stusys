package fun.faulkner.stusys.controller;

import fun.faulkner.stusys.entity.Score;
import fun.faulkner.stusys.entity.Student;
import fun.faulkner.stusys.service.ScoreServiceImpl;
import fun.faulkner.stusys.service.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/scores")
public class AdminScoresServlet extends HttpServlet {
    private ScoreServiceImpl scoreService;
    private StudentServiceImpl studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        scoreService = new ScoreServiceImpl(new fun.faulkner.stusys.dao.ScoreDaoImpl());
        studentService = new StudentServiceImpl(new fun.faulkner.stusys.dao.StudentDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String studentId = req.getParameter("studentId");

        try {
            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                boolean success = scoreService.deleteScore(id);
                if (success) {
                    req.setAttribute("success", "删除成绩成功");
                } else {
                    req.setAttribute("error", "删除成绩失败");
                }
            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Score score = scoreService.getScoreById(id);
                req.setAttribute("editScore", score);
            }

            List<Student> students = studentService.getAllStudents();
            req.setAttribute("students", students);

            if (studentId != null && !studentId.isEmpty()) {
                List<Score> scores = scoreService.getScoreByStudentId(studentId);
                req.setAttribute("scores", scores);
                req.setAttribute("selectedStudentId", studentId);
            }
        } catch (Exception e) {
            req.setAttribute("error", "操作失败: " + e.getMessage());
        }

        req.getRequestDispatcher("/WEB-INF/admin/scores.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                String studentId = req.getParameter("studentId");
                String courseId = req.getParameter("courseId");
                double scoreValue = Double.parseDouble(req.getParameter("score"));

                Score score = new Score(0, courseId, studentId, scoreValue);
                boolean success = scoreService.addScore(score);
                if (success) {
                    req.setAttribute("success", "添加成绩成功");
                } else {
                    req.setAttribute("error", "添加成绩失败");
                }
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Score score = scoreService.getScoreById(id);
                score.setScore(Double.parseDouble(req.getParameter("score")));

                boolean success = scoreService.updateScore(score);
                if (success) {
                    req.setAttribute("success", "更新成绩成功");
                } else {
                    req.setAttribute("error", "更新成绩失败");
                }
            }
        } catch (Exception e) {
            req.setAttribute("error", "操作失败: " + e.getMessage());
        }

        doGet(req, resp);
    }
}