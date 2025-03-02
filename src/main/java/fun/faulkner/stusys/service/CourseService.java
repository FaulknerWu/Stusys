package fun.faulkner.stusys.service;

import fun.faulkner.stusys.entity.Course;
import java.util.List;

public interface CourseService {
    Course getCourseByCourseId(String courseId);
    boolean insertCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(String courseId);
    List<Course> getAllCourses();
}