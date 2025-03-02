package fun.faulkner.stusys.controller;

import fun.faulkner.stusys.entity.Course;
import fun.faulkner.stusys.service.CourseService;
import fun.faulkner.stusys.service.CourseServiceImpl;
import fun.faulkner.stusys.dao.CourseDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/courses")
public class AdminCoursesServlet extends HttpServlet {
    private CourseService courseService;

    @Override
    public void init() {
        courseService = new CourseServiceImpl(new CourseDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("delete".equals(action)) {
                String courseId = req.getParameter("courseId");
                Course course = courseService.getCourseByCourseId(courseId);
                if (course != null) {
                    courseService.deleteCourse(courseId);
                    req.setAttribute("success", "删除课程成功");
                }
            } else if ("edit".equals(action)) {
                String courseId = req.getParameter("courseId");
                Course course = courseService.getCourseByCourseId(courseId);
                req.setAttribute("editCourse", course);
            }

            List<Course> courses = courseService.getAllCourses();
            req.setAttribute("courses", courses);
        } catch (Exception e) {
            req.setAttribute("error", "操作失败: " + e.getMessage());
        }

        req.getRequestDispatcher("/WEB-INF/admin/courses.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                Course newCourse = new Course(
                        0,
                        req.getParameter("courseId"),
                        req.getParameter("courseName"),
                        req.getParameter("teacherName"),
                        Integer.parseInt(req.getParameter("credits"))
                );
                boolean success = courseService.insertCourse(newCourse);
                if (success) {
                    req.setAttribute("success", "添加课程成功");
                } else {
                    req.setAttribute("error", "添加课程失败");
                }
            } else if ("update".equals(action)) {
                String originalCourseId = req.getParameter("originalCourseId");
                Course existingCourse = courseService.getCourseByCourseId(originalCourseId);

                if (existingCourse != null) {
                    existingCourse.setCourseId(req.getParameter("courseId"));
                    existingCourse.setCourseName(req.getParameter("courseName"));
                    existingCourse.setTeacherName(req.getParameter("teacherName"));
                    existingCourse.setCredits(Integer.parseInt(req.getParameter("credits")));
                    boolean success = courseService.updateCourse(existingCourse);
                    if (success) {
                        req.setAttribute("success", "更新课程信息成功");
                    } else {
                        req.setAttribute("error", "更新课程信息失败");
                    }
                }
            }
        } catch (Exception e) {
            req.setAttribute("error", "操作失败: " + e.getMessage());
        }

        doGet(req, resp);
    }
}