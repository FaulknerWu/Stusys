package fun.faulkner.stusys.controller;

import fun.faulkner.stusys.dao.StudentDaoImpl;
import fun.faulkner.stusys.entity.Course;
import fun.faulkner.stusys.entity.Score;
import fun.faulkner.stusys.entity.Student;
import fun.faulkner.stusys.entity.User;
import fun.faulkner.stusys.service.StudentService;
import fun.faulkner.stusys.service.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user/info")
public class InfoServlet extends HttpServlet {
    private final StudentService studentService = new StudentServiceImpl(new StudentDaoImpl());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if ("user".equals(user.getRole())) {
            String studentId = user.getUsername();
            Student student = studentService.getStudentByStudentId(studentId);
            String userName = student.getName();
            request.setAttribute("userName", userName);
            request.setAttribute("user", studentService.getStudentByStudentId(studentId));
            request.getRequestDispatcher("/WEB-INF/user/info.jsp").forward(request, response);
        } else if ("admin".equals(user.getRole())) {
            String userName = user.getUsername();
            request.setAttribute("userName", userName);
            request.setAttribute("errorMessage", "管理员无信息数据");
            request.getRequestDispatcher("/WEB-INF/user/info.jsp").forward(request, response);
        }
    }
}