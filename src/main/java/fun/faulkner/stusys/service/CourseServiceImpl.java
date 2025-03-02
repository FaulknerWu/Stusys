package fun.faulkner.stusys.service;

import fun.faulkner.stusys.dao.CourseDaoImpl;
import fun.faulkner.stusys.entity.Course;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final CourseDaoImpl courseDao;

    public CourseServiceImpl(CourseDaoImpl courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course getCourseByCourseId(String courseId) {
        return courseDao.getCourseByCourseId(courseId);
    }

    @Override
    public boolean insertCourse(Course course) {
        try {
            Course result = courseDao.insertCourse(course);
            return result != null;
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCourse(Course course) {
        try {
            int affectedRows = courseDao.updateCourse(course);
            return affectedRows > 0;
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCourse(String courseId) {
        try {
            int affectedRows = courseDao.deleteCourse(courseId);
            return affectedRows > 0;
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }
}