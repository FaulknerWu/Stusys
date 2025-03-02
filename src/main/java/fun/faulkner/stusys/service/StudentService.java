package fun.faulkner.stusys.service;

import fun.faulkner.stusys.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(int id);
    Student getStudentByStudentId(String studentId);
    boolean addStudent(String studentId, String className, String name, int age, String gender, String phone);
    boolean updateStudent(Student student);
    boolean deleteStudent(Student student);
}