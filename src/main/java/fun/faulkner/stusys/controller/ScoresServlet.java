package fun.faulkner.stusys.controller;

import fun.faulkner.stusys.dao.StudentDaoImpl;
import fun.faulkner.stusys.dao.UserDaoImpl;
import fun.faulkner.stusys.entity.Course;
import fun.faulkner.stusys.entity.Score;
import fun.faulkner.stusys.entity.Student;
import fun.faulkner.stusys.entity.User;
import fun.faulkner.stusys.service.*;
import fun.faulkner.stusys.dao.ScoreDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/scores")
public class ScoresServlet extends HttpServlet {
    private final ScoreService scoreService = new ScoreServiceImpl(new ScoreDaoImpl());
    private final StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());
    private final CourseService courseService = new CourseServiceImpl(new fun.faulkner.stusys.dao.CourseDaoImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String role = user.getRole();
        String userName = "";

        if ("user".equals(role)) {
            String studentId = user.getUsername();
            List<Score> scores = scoreService.getScoreByStudentId(studentId);
            List<Course> courses = new ArrayList<>();

            for (Score score : scores) {
                courses.add(courseService.getCourseByCourseId(score.getCourseId()));
            }
            Student student = studentService.getStudentByStudentId(studentId);
            userName = student.getName();

            request.setAttribute("courses", courses);
            request.setAttribute("scores", scores);
            request.setAttribute("user", student);

        } else if ("admin".equals(role)) {
            userName = user.getUsername();
            request.setAttribute("errorMessage", "管理员无成绩数据");
            request.setAttribute("scores", new ArrayList<>());
            request.setAttribute("courses", new ArrayList<>());
        }

        request.setAttribute("userName", userName);
        request.getRequestDispatcher("/WEB-INF/user/scores.jsp").forward(request, response);
    }
}