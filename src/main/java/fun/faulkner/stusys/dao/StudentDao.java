package fun.faulkner.stusys.dao;

import fun.faulkner.stusys.entity.Student;
import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();
    Student getById(int id);
    Student getByStudentId(String studentId);
    boolean addStudent(String studentId, String className, String name, int age, String gender, String phone);
    boolean updateStudent(Student student);
    boolean deleteStudent(Student student);
}


