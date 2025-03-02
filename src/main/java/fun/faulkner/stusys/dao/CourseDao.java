package fun.faulkner.stusys.dao;

import fun.faulkner.stusys.entity.Course;
import java.util.List;

public interface CourseDao {
    Course getCourseByCourseId(String courseId);
    Course insertCourse(Course course);
    int updateCourse(Course course);
    int deleteCourse(String courseId);
    List<Course> getAllCourses();
}