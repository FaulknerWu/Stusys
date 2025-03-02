package fun.faulkner.stusys.dao;

import fun.faulkner.stusys.entity.Student;
import fun.faulkner.stusys.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDaoImpl implements StudentDao {
    private static final Logger LOGGER = Logger.getLogger(StudentDaoImpl.class.getName());

    private Student mapResultSetToStudent(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String StudentId = resultSet.getString("student_id");
        String className = resultSet.getString("class_name");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String gender = resultSet.getString("gender");
        String phone = resultSet.getString("phone");

        return new Student(id, StudentId, className, name, age, gender, phone);
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                students.add(mapResultSetToStudent(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching students", e);
            throw new RuntimeException("Error fetching students", e);
        }
        return students;
    }

    @Override
    public Student getById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        Student student = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    student = mapResultSetToStudent(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching student", e);
            throw new RuntimeException("Error fetching student", e);
        }
        return student;
    }

    @Override
    public Student getByStudentId(String studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        Student student = null;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    student = mapResultSetToStudent(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching student", e);
            throw new RuntimeException("Error fetching student", e);
        }
        return student;
    }

    @Override
    public boolean addStudent(String studentId, String className, String name, int age, String gender, String phone) {
        String sql = "INSERT INTO students (student_id, class_name, name, age, gender, phone) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            Timestamp timestamp = Timestamp.valueOf(java.time.LocalDateTime.now());
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, className);
            preparedStatement.setString(3, name);
            preparedStatement.setInt(4, age);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, phone);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding student", e);
            throw new RuntimeException("Error adding student", e);
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, class_name = ? ,age = ?, gender = ?, phone = ? WHERE id = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getClassName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getGender());
            preparedStatement.setString(5, student.getPhone());
            preparedStatement.setInt(6, student.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating student", e);
            throw new RuntimeException("Error updating student", e);
        }
    }

    @Override
    public boolean deleteStudent(Student student) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, student.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting student", e);
            throw new RuntimeException("Error deleting student", e);
        }
    }
}
