package fun.faulkner.stusys.dao;

import fun.faulkner.stusys.entity.Course;
import fun.faulkner.stusys.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDaoImpl implements CourseDao {
    private static final Logger LOGGER = Logger.getLogger(CourseDaoImpl.class.getName());

    @Override
    public Course getCourseByCourseId(String courseId) {
        String sql = "SELECT * FROM courses WHERE course_id = ?";
        Course course = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    course = mapResultSetToCourse(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching course", e);
            throw new RuntimeException("Error fetching course", e);
        }
        return course;
    }

    @Override
    public Course insertCourse(Course course) {
        String sql = "INSERT INTO courses (course_id, course_name, teacher_name, credits) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, course.getCourseId());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setString(3, course.getTeacherName());
            preparedStatement.setInt(4, course.getCredits());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating course failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Course(
                            generatedKeys.getInt(1),
                            course.getCourseId(),
                            course.getCourseName(),
                            course.getTeacherName(),
                            course.getCredits()
                    );
                } else {
                    throw new SQLException("Creating course failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error creating course", e);
            throw new RuntimeException("Error creating course", e);
        }
    }

    @Override
    public int updateCourse(Course course) {
        String sql = "UPDATE courses SET course_id = ?, course_name = ?, teacher_name = ?, credits = ? WHERE id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, course.getCourseId());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setString(3, course.getTeacherName());
            preparedStatement.setInt(4, course.getCredits());
            preparedStatement.setInt(5, course.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating course", e);
            throw new RuntimeException("Error updating course", e);
        }
    }

    @Override
    public int deleteCourse(String courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, courseId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting course", e);
            throw new RuntimeException("Error deleting course", e);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM courses";
        List<Course> courses = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                courses.add(mapResultSetToCourse(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching courses", e);
            throw new RuntimeException("Error fetching courses", e);
        }
        return courses;
    }

    private Course mapResultSetToCourse(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String courseId = resultSet.getString("course_id");
        String courseName = resultSet.getString("course_name");
        String teacherName = resultSet.getString("teacher_name");
        int credits = resultSet.getInt("credits");

        return new Course(id, courseId, courseName, teacherName, credits);
    }
}