package fun.faulkner.stusys.controller;

import fun.faulkner.stusys.entity.Student;
import fun.faulkner.stusys.service.StudentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/students")
public class AdminStudentsServlet extends HttpServlet {
    private StudentServiceImpl studentService;

    @Override
    public void init() throws ServletException {
        super.init();
        studentService = new StudentServiceImpl(new fun.faulkner.stusys.dao.StudentDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Student student = studentService.getStudentById(id);
                if (student != null) {
                    studentService.deleteStudent(student);
                    req.setAttribute("success", "删除学生成功");
                }
            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Student student = studentService.getStudentById(id);
                req.setAttribute("editStudent", student);
            }

            List<Student> students = studentService.getAllStudents();
            req.setAttribute("students", students);
        } catch (Exception e) {
            req.setAttribute("error", "操作失败: " + e.getMessage());
        }

        req.getRequestDispatcher("/WEB-INF/admin/students.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                String studentId = req.getParameter("studentId");
                String className = req.getParameter("className");
                String name = req.getParameter("name");
                int age = Integer.parseInt(req.getParameter("age"));
                String gender = req.getParameter("gender");
                String phone = req.getParameter("phone");

                boolean success = studentService.addStudent(studentId, className, name, age, gender, phone);
                if (success) {
                    req.setAttribute("success", "添加学生成功");
                } else {
                    req.setAttribute("error", "添加学生失败");
                }
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Student student = studentService.getStudentById(id);

                student.setClassName(req.getParameter("className"));
                student.setName(req.getParameter("name"));
                student.setAge(Integer.parseInt(req.getParameter("age")));
                student.setGender(req.getParameter("gender"));
                student.setPhone(req.getParameter("phone"));

                boolean success = studentService.updateStudent(student);
                if (success) {
                    req.setAttribute("success", "更新学生信息成功");
                } else {
                    req.setAttribute("error", "更新学生信息失败");
                }
            }
        } catch (Exception e) {
            req.setAttribute("error", "操作失败: " + e.getMessage());
        }

        doGet(req, resp);
    }
}